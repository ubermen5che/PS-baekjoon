import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map = new int[499][499];
    static int[] dy = new int[] {0, 1, 0, -1}; //왼, 아래, 오른쪽, 위쪽
    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] portion = new int[] {1, 1, 2, 2, 5, 7, 7, 10, 10};
    static int[][] portionY = {
            {-1, 1, -2, 2, 0, -1, 1, -1, 1}, //왼
            {-1, -1, 0, 0, 2, 0, 0, 1, 1}, //아래
            {1, -1, 2, -2, 0, 1, -1, 1, -1}, //오른쪽
            {1, 1, 0, 0, -2, 0, 0, -1, -1}  //위쪽
    };
    static int[][] portionX = {
            {1, 1, 0, 0, -2, 0, 0, -1, -1}, //왼
            {-1, 1, -2, 2, 0, -1, 1, -1, 1}, //아래
            {-1, -1, 0, 0, 2, 0, 0, 1, 1}, //오른쪽
            {1, -1, 2, -2, 0, 1, -1, 1, -1}  //위쪽
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve(N / 2, N / 2));
    }

    static int disperse(int y, int x, int dir) {
        // 총 9개 영역에 대해서 모래를 이동시킨다.
        int ret = 0;
        int sand = map[y][x];
        int sum = 0;

        for (int i = 0; i < 9; i++) {
            int nY = y + portionY[dir][i];
            int nX = x + portionX[dir][i];
            int windSand = (sand * portion[i] / 100);
            sum += windSand;

            if (nY < 0 || nY >= N || nX < 0 || nX >= N) {
                ret += windSand;
                continue;
            }

            map[nY][nX] += windSand;
        }

        int nY = y + dy[dir];
        int nX = x + dx[dir];
        int a = (sand - sum);
        if (nY < 0 || nY >= N || nX < 0 || nX >= N) {
            ret += a;
        } else {
            map[nY][nX] += a;
        }

        map[y][x] = 0;
        return ret;
    }

    static int solve(int cY, int cX) {
        boolean visited[][] = new boolean[499][499];
        int ret = 0;
        int dir = -1;
        while((cY != 0) || (cX != 0)) {
            //System.out.println(cY + " " + cX);
            visited[cY][cX] = true;
            int nDir = (dir + 1) % 4;
            int nY = cY + dy[nDir];
            int nX = cX + dx[nDir];
            if (visited[nY][nX]) {
                nDir = dir;
                nY = cY + dy[nDir];
                nX = cX + dx[nDir];
            }

            ret += disperse(nY, nX, nDir);
            cY = nY;
            cX = nX;
            dir = nDir;
        }

        return ret;
    }
}