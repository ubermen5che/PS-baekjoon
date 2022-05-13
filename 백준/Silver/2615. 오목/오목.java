import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] board = new int[19][19];
    static int[] dy = new int[] {0, 1, 1, 1, 0, -1, -1, -1}; //동, 남동, 남, 남서, 서, 북서, 북, 북동
    static int[] dx = new int[] {1, 1, 0, -1, -1, -1, 0, 1};
    static int xPos = 18;
    static int yPos = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // board전체를 돌면서 dfs
        for (int y = 0; y < 19; y++) {
            for (int x = 0; x < 19; x++) {
                if (board[y][x] == 0) continue;
                for (int dir = 0; dir < 8; dir++) {
                    int numOfDol = 0;

                    xPos = 19;
                    numOfDol += dfs(y, x, dir, board[y][x], 0);
                    numOfDol += dfs(y, x, ((dir + 4) % 8), board[y][x], 0);
                    numOfDol--;
                    if (numOfDol == 5) {
                        sb.append(board[y][x]).append("\n");
                        findMostLeftPos(y, x, dir);
                        yPos += 1;
                        xPos += 1;
                        sb.append(yPos + " " + xPos);
                        System.out.println(sb);
                        System.exit(0);
                    }
                }
            }
        }

        System.out.println(0);
    }

    static void findMostLeftPos(int curY, int curX, int dir) {

    }
    static int dfs(int y, int x, int dir, int color, int cnt) {
        if (y < 0 || y >= 19 || x < 0 || x >= 19) return cnt;

        if (board[y][x] == 0 || board[y][x] != color) {
            return cnt;
        }

        if (xPos > x) {
            xPos = x;
            yPos = y;
        }

        int nY = y + dy[dir];
        int nX = x + dx[dir];

        return dfs(nY, nX, dir, color, cnt + 1);
    }
}