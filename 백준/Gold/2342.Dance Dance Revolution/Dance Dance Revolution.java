import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][][] Dy;
    static int[] nextDir;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int dirCnt = 1;
        nextDir = new int[100001];
        while (true) {
            int dir = Integer.parseInt(st.nextToken());
            if (dir == 0) {
                dirCnt--;
                break;
            }
            nextDir[dirCnt] = dir;
            dirCnt++;
        }

        Dy = new int[dirCnt + 1][5][5];

        for (int i = 1; i <= dirCnt; i++) {
            for (int j = 0; j <= 4; j++) {
                for (int k = 0; k <= 4; k++) {
                    Dy[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        Dy[1][nextDir[1]][0] = 2; //처음에 왼쪽으로 다음 화살표 밟는 경우
        Dy[1][0][nextDir[1]] = 2; //오른쪽으로 밟는 경우

        for (int i = 1; i < dirCnt; i++) {
            for (int j = 0; j <= 4; j++) {
                for (int k = 0; k <= 4; k++) {
                    int next = nextDir[i + 1];
                    if (Dy[i][j][k] != Integer.MAX_VALUE) {
                        // next : 2
                        // current state : (1,4)
                        // move case 1 : (1,4) -> (1, 2)
                        if (next != j) {
                            Dy[i+1][j][next] = Math.min(Dy[i+1][j][next], Dy[i][j][k] + getCost(k, next));
                        }
                        //      case 2 : (1,4) -> (2, 4)
                        if (next != k) {
                            Dy[i+1][next][k] = Math.min(Dy[i+1][next][k], Dy[i][j][k] + getCost(j, next));
                        }
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                answer = Math.min(answer, Dy[dirCnt][i][j]);
            }
        }

        System.out.println(answer);
    }


    static int getCost (int from, int to){
        if(from == to) {
            return 1;
        } else if(from == 0) {
            return 2;
        } else if (Math.abs(from - to) == 2) {
            return 4;
        } else {
            return 3;
        }
    }

    /**
    static int getCost(int j, int k, int next) {
        //중앙에 있던발이 다른 구역으로 이동하는 경우
        if (j == 0 && j != next || k == 0 && k != next)
            return 2;
        //발이 대각으로 이동한 경우
        else if (Math.abs(j - next) == 2 || Math.abs(k - next) == 2)
            return 4;
        //인접한 곳으로 이동한 경우
        else if ((j % 4) + 1 == next || (k % 4) + 1 == next)
            return 3;
        //같은 지점을 밟을 경우
        else if (j == next || k == next)
            return 1;

        return 0;
    }
     **/
}