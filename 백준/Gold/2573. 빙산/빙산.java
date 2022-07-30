import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, areaCnt, year;
    static int[][] map;
    static int[][] area;
    static int numOfIce = 0;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        area = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) numOfIce++;
            }
        }

        while(true) {
            if (check()) {
                break;
            }
            melt();
            year++;
        }

        if (numOfIce == 0) {
            System.out.println(0);
        } else
            System.out.println(year);
    }

    static void copyArr(int[][] src, int [][] dest) {
        for (int i = 1; i < N-1; i++) {
            for (int j = 1; j < M-1; j++) {
                dest[i][j] = src[i][j];
            }
        }
    }
    static void melt() {
        int tmp[][] = new int[N][M];

        copyArr(map, tmp);

        for (int y = 1; y < N-1; y++) {
            for (int x = 1; x < M-1; x++) {
                if (map[y][x] != 0) {
                    int waterCnt = 0;
                    // 동서남북 돌면서 0인 영역 cnt하고 현재 얼음 녹임
                    for (int d = 0; d < 4; d++) {
                        int nY = y + dy[d];
                        int nX = x + dx[d];
                        if (nY < 0 || nY >= N || nX < 0 || nX >= M) continue;
                        if (map[nY][nX] == 0) waterCnt++;
                    }

                    tmp[y][x] -= waterCnt;
                    if (tmp[y][x] <= 0) {
                        tmp[y][x] = 0;
                        numOfIce--;
                    }
                }
            }
        }

        copyArr(tmp, map);
    }

    static boolean check() {
        area = new int[N][M];
        areaCnt = 0;
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                // 방문하지 않았으면 dfs해서 영역 표시
                if(map[i][j] != 0 && area[i][j] == 0) {
                    areaCnt++;
                    dfs(i, j);
                }
                if (areaCnt >= 2) return true;
            }
        }

        if (areaCnt >= 2 || numOfIce <= 0) return true;
        else return false;
    }

    static void dfs(int y, int x) {
        // 바다 이거나 이미 영역이 표시된 곳이라면
        if (map[y][x] == 0 || area[y][x] != 0) return;

        area[y][x] = areaCnt;

        for (int i = 0; i < 4; i++) {
            int nY = y + dy[i];
            int nX = x + dx[i];
            if (nY < 0 || nY >= N || nX < 0 || nX >= M) continue;
            dfs(nY, nX);
        }
    }
}