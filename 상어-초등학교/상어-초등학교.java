import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Desk classRoom[][] = new Desk[401][401];
    static int[] dx = new int[] {1, 0, -1, 0}; //동, 남, 서, 북
    static int[] dy = new int[] {0, 1, 0 ,-1};
    static HashMap<Integer, ArrayList<Integer>> likeInfo = new HashMap<>();
    static ArrayList<Integer> order = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                classRoom[i][j] = new Desk(0, new int[(N * N) + 1], 0);
            }
        }

        StringTokenizer st;

        for (int i = 0; i < N * N; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            int key;

            st = new StringTokenizer(bf.readLine());
            key = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                tmp.add(Integer.parseInt(st.nextToken()));
            }

            likeInfo.put(key, tmp);
            order.add(key);
        }

        //빈자리 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int dirX, dirY;
                for (int k = 0; k < 4; k++) {
                    dirX = j + dx[k];
                    dirY = i + dy[k];

                    if (dirX <= 0 || dirX > N || dirY <= 0 || dirY > N)
                        continue;

                    //빈자리 일 경우
                    if (classRoom[dirY][dirX].studentID == 0) {
                        classRoom[i][j].adjEmpty++;
                    }
                }
            }
        }

        while(true) {
            if (order.isEmpty())
                break;

            int student = order.remove(0);
            ArrayList<Integer> tmpLike = likeInfo.get(student);

            //현재 orderList에서 뽑아온 자리배치해야할 순번의 학생을 각 자리에 앉혔을 때 인근에 좋아하는 친구의 수가 몇명인지 계산
            //계산한 값을 Desk 클래스에 adjLike에 저장시킨 후 추후 이 값을 통해 자리 결정

            int max = Integer.MIN_VALUE;

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (classRoom[i][j].studentID != 0)
                        continue;
                    int dirX, dirY;
                    for (int k = 0; k < 4; k++) {
                        dirX = j + dx[k];
                        dirY = i + dy[k];

                        if (dirX <= 0 || dirX > N || dirY <= 0 || dirY > N)
                            continue;

                        //인접한 자리에 좋아하는 친구가 있을경우
                        if (tmpLike.contains(classRoom[dirY][dirX].studentID)) {
                            classRoom[i][j].adjLike[student]++;
                        }
                    }

                    //이곳에서 max값 갱신하며 최대 좋아하는 친구수 저장.
                    //이 값을 토대로 다시 classRoom순회하며 만약 최대값이 하나라면 그 자리에 배치
                    //여러개라면 row , col 저장한 정보토대로 자리 결정
                    max = Math.max(max, classRoom[i][j].adjLike[student]);
                }
            }

            ArrayList<Integer> rowList = new ArrayList<>();
            ArrayList<Integer> colList = new ArrayList<>();

            //max가 여러개인지 확인 max가 여러개라면 max인 칸중 빈칸이 가장 많은 자리를 자리로 선택한다.
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (classRoom[i][j].studentID == 0 && max == classRoom[i][j].adjLike[student]) {
                        rowList.add(i);
                        colList.add(j);
                    }
                }
            }

            int maxPos[] = new int[2];

            //max가 여러개인 경우 빈칸의 갯수가 가장 많은 자리로 배치해준다.
            if (!rowList.isEmpty()) {
                if (rowList.size() > 1) {
                    //빈칸 갯수 최대값 얻기
                    int emptyMax = Integer.MIN_VALUE;
                    for (int k = 0; k < rowList.size(); k++) {
                        emptyMax = Math.max(emptyMax, classRoom[rowList.get(k)][colList.get(k)].adjEmpty);
                    }

                    //빈칸 max인 row,col만 추출
                    ArrayList<Integer> tmpRowList = new ArrayList<>();
                    ArrayList<Integer> tmpColList = new ArrayList<>();

                    for (int k = 0; k < rowList.size(); k++) {
                        if (emptyMax == classRoom[rowList.get(k)][colList.get(k)].adjEmpty) {
                            tmpRowList.add(rowList.get(k));
                            tmpColList.add(colList.get(k));
                        }
                    }

                    if (tmpRowList.size() == 1 && tmpColList.size() == 1) { // 최대 빈칸을 가지는 row,col이 하나만 존재함.
                        maxPos[0] = tmpRowList.get(0);
                        maxPos[1] = tmpColList.get(0);
                        classRoom[maxPos[0]][maxPos[1]].studentID = student;
                        updateEmptyDesk(maxPos);
                    } else if (tmpRowList.size() > 1 && tmpColList.size() > 1 && tmpColList.size() == tmpRowList.size()){
                        int minCol = 0;
                        int idx = 0;
                        for (int i = 0; i < tmpColList.size(); i++) {
                            minCol = Math.min(minCol, tmpColList.get(i));
                        }

                        for (int i = 0; i < tmpColList.size(); i++) {
                            if (tmpColList.get(i) == minCol) {
                                idx = i;
                                break;
                            }
                        }
                        maxPos[0] = tmpRowList.get(idx);
                        maxPos[1] = tmpColList.get(idx);
                        classRoom[maxPos[0]][maxPos[1]].studentID = student;
                        updateEmptyDesk(maxPos);
                    }
                } else if (rowList.size() == 1 && colList.size() == 1) {
                    maxPos[0] = rowList.get(0);
                    maxPos[1] = colList.get(0);
                    classRoom[maxPos[0]][maxPos[1]].studentID = student;
                    updateEmptyDesk(maxPos);
                }
            } else {
                continue;
            }
        }

        int score = 0;
        int likeCnt = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                likeCnt = 0;
                int dirX, dirY;

                if (classRoom[i][j].studentID == 0)
                    continue;

                ArrayList<Integer> tmp = likeInfo.get(classRoom[i][j].studentID);
                for (int k = 0; k < dx.length; k++) {
                    dirX = j + dx[k];
                    dirY = i + dy[k];

                    if (dirX <= 0 || dirX > N || dirY <= 0 || dirY > N)
                        continue;

                    if (classRoom[dirY][dirX].studentID == 0)
                        continue;

                    if (tmp.contains(classRoom[dirY][dirX].studentID))
                        likeCnt++;
                }

                if (likeCnt == 1)
                    score += 1;
                else if (likeCnt == 2)
                    score += 10;
                else if (likeCnt == 3)
                    score += 100;
                else if (likeCnt == 4)
                    score += 1000;
            }
        }

        /**
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(classRoom[i][j].studentID + " ");
            }
            System.out.println();
        }
         **/
        System.out.println(score);
    }

    static public void updateEmptyDesk(int[] maxPos) {

        //인근 칸 빈칸 갯수 업데이트해주기
        int dirX, dirY;
        for (int k = 0; k < 4; k++) {
            dirX = maxPos[1] + dx[k];
            dirY = maxPos[0] + dy[k];

            if (dirX <= 0 || dirX > N || dirY <= 0 || dirY > N)
                continue;

            if (classRoom[dirY][dirX].adjEmpty > 0)
                classRoom[dirY][dirX].adjEmpty -= 1;
        }
    }
    static public class Desk {
        int adjEmpty;
        int[] adjLike;
        int studentID;

        public Desk(int adjEmpty, int[] adjLike, int studentID) {
            this.adjEmpty = adjEmpty;
            this.adjLike = adjLike;
            this.studentID =  studentID;
        }
    }
}