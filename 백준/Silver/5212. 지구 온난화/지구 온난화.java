import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    static int[] dx = new int[] {1, 0, -1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};
    static int maxRow, maxCol;
    static int minRow, minCol;
    static ArrayList<int[]> xyList = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R + 1][C + 1];
        maxRow = Integer.MIN_VALUE;
        maxCol = Integer.MIN_VALUE;
        minRow = Integer.MAX_VALUE;
        minCol = Integer.MAX_VALUE;

        for (int i = 1; i <= R; i++) {
            String line = br.readLine();
            for (int j = 1; j <= C; j++) {
                map[i][j] = line.charAt(j - 1);
            }
        }

        // 50년 후 잠기지 않는 섬의 좌표 체크
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] == '.') continue;

                //섬이라면 인접한 4방향 체크해서 바다가 3개이상 인접한 경우는 제외.
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nY = i + dy[k];
                    int nX = j + dx[k];

                    if (nY <= 0 || nY > R || nX <= 0 || nX > C) {
                        cnt++;
                        continue;
                    }

                    if (map[nY][nX] == '.')
                        cnt++;
                }

                // (i, j)에 위치한 섬은 물에 잠기지 않는 섬이다.
                if (cnt < 3) {
                    xyList.add(new int[] {i, j});
                    maxRow = Math.max(maxRow, i);
                    maxCol = Math.max(maxCol, j);
                    minRow = Math.min(minRow, i);
                    minCol = Math.min(minCol, j);
                }
            }
        }

        // min, max 행,열 정보 기반으로 정답 찍어내기
        char[][] answerMap = new char[maxRow + 1][maxCol + 1];

        for (int i = 0; i < xyList.size(); i++) {
            int[] xy = xyList.get(i);
            int y = xy[0];
            int x = xy[1];

            answerMap[y][x] = 'X';
        }

        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                if (answerMap[i][j] == 'X')
                    continue;
                else
                    answerMap[i][j] = '.';
            }
        }

        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                System.out.print(answerMap[i][j]);
            }
            System.out.println();
        }
    }
}