import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int map[][];
    static boolean visit[][];
    static int N,M;
    static int dx[] = new int[] {1, 0, -1, 0}; //동,남,서,북
    static int dy[] = new int[] {0, 1, 0, -1};
    static int max = Integer.MIN_VALUE;
    static int sum[];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M ; j++) {
                sum = new int[4];
                visit[i][j] = true;
                sum[0] = map[i][j];
                dfs(1, i, j);
                visit[i][j] = false;

                int mountainSum = searchMountainShape(i, j);
                max = Math.max(mountainSum, max);
            }
        }
        System.out.println(max);
    }

    static public void dfs(int depth, int y, int x) {
        if (depth == 4) { //정사각 4개 모두 탐색한 경우 종료
            int sumOfFourCell = 0;

            for (int i = 0; i < 4; i++)
                sumOfFourCell += sum[i];

            max = Math.max(max, sumOfFourCell);
            return;
        }

        int dirX, dirY;

        for (int i = 0; i < 4; i++) {
            dirX = x + dx[i];
            dirY = y + dy[i];

            if (dirX >= 0 && dirX < M && dirY >= 0 && dirY < N && !visit[dirY][dirX]) {
                sum[depth] = map[dirY][dirX];
                visit[dirY][dirX] = true;
                dfs(depth+1, dirY, dirX);
                visit[dirY][dirX] = false;
            }
        }
    }

    static public int searchMountainShape(int y, int x) {
        int dirX, dirY;
        int wing = 4;
        int sum = map[y][x];
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            dirX = x + dx[i];
            dirY = y + dy[i];

            //날개가 2개이상 없다면 ㅗ 모양이 아니다. 함수 종료
            if (wing <= 2)
                return 0;

            if (dirX >= 0 && dirX < M && dirY >= 0 && dirY < N) {
                sum += map[dirY][dirX];
                min = Math.min(min, map[dirY][dirX]);
            } else  //날개가 범위 밖에있으면 제외
                wing--;
        }

        if (wing == 4) {
            sum = sum - min;
        }

        return sum;
    }
}