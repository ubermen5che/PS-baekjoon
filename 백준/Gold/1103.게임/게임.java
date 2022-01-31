import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static String[][] map;
    static boolean[][] visit;
    static int[] dx = new int[] {1, -1, 0, 0};
    static int[] dy = new int[] {0, 0, 1, -1};
    static int[][] Dy;
    static int N, M, answer;
    static boolean loop = false;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[N + 1][M + 1];
        visit = new boolean[N + 1][M + 1];
        Dy = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = String.valueOf(line.charAt(j-1));
            }
        }

        answer = 0;
        visit[1][1] = true;
        dfs(1, 1);
        if (loop)
            System.out.println(-1);
        else
            System.out.println(answer);
    }

    static void dfs(int curX, int curY) {
        int curNum = Integer.parseInt(map[curY][curX]);

        for (int i = 0; i < 4; i++) {
            int nX = curX + dx[i] * curNum;
            int nY = curY + dy[i] * curNum;
            if (nX <= 0 || nX > M || nY <= 0 || nY > N) {
                answer = Math.max(answer, Dy[curY][curX] + 1);
                continue;
            }

            if (map[nY][nX].equals("H")) {
                answer = Math.max(answer, Dy[curY][curX] + 1);
                continue;
            }

            if (visit[nY][nX]) {
                loop = true;
                return;
            }

            //출발지점에서 현재위치까지 도달할 때 까지 이동한 최대횟수가 다음 이동할 곳의 이동횟수보다 크다면 이동하고
            //그렇지 않으면 제외 -> 백트래킹
            if (Dy[nY][nX] < Dy[curY][curX] + 1) {
                Dy[nY][nX] = Dy[curY][curX] + 1;
                answer = Math.max(Dy[nY][nX], answer);
                visit[nY][nX] = true;
                dfs(nX, nY);
                visit[nY][nX] = false;
            }
        }
    }
}