import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시간 제한 : 1초
 * 메모리 제한 : 128Mb
 *
 * 시간 복잡도 : 각 칸이 공기에 노출되었는지 확인하는 연산이 O(N^2)
 * 모두 사라지기 1시간전 치즈 조각들을 구하는데에 O(N^2)이므로 시간복잡도는 O(N^2) N은 최대 100임
 */
public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static int[] dy = new int[] {0, 1, 0, -1};
    static int[] dx = new int[] {1, 0, -1, 0};
    static int time = 0, fraction = 0;
    static int cheeseCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) cheeseCnt++;
            }
        }
        int numOfCheese = cheeseCnt;

        if (numOfCheese == 0) {
            System.out.println(0);
            System.out.println(0);
            System.exit(0);
        }

        int[][] backup = new int[N][M];

        while(true) {
            copyArr(backup, map);
            time++;
            visit = new boolean[N][M];
            numOfCheese -= melt();

            if (numOfCheese <= 0) {
                // 치즈 조각을 계산
                visit = new boolean[N][M];
                for (int y = 0; y < N; y++) {
                    for (int x = 0; x < M; x++) {
                        if (backup[y][x] == 1) {
                            fraction++;
                        }
                    }
                }
                break;
            }
        }

        System.out.println(time);
        System.out.println(fraction);
    }

    static int melt() {
        int backup[][] = new int[N][M];
        int meltCnt = 0;
        Queue<Integer> Q = new LinkedList<>();

        //좌상단 시작점
        Q.offer(0);
        Q.offer(0);

        // 우상단 시작점
        Q.offer(0);
        Q.offer(M - 1);

        //좌하단 시작점
        Q.offer(N - 1);
        Q.offer(0);

        //우하단 시작점
        Q.offer(N - 1);
        Q.offer(M - 1);

        copyArr(backup, map);

        while(!Q.isEmpty()) {
            int cY = Q.poll();
            int cX = Q.poll();

            for (int i = 0; i < 4; i++) {
                int nY = cY + dy[i];
                int nX = cX + dx[i];

                if (nY < 0 || nY >= N || nX < 0 || nX >= M) continue;
                if (visit[nY][nX]) continue;
                if (map[nY][nX] == 1) {
                    meltCnt++;
                    visit[nY][nX] = true;
                    backup[nY][nX] = 0;
                } else {
                    visit[nY][nX] = true;
                    Q.offer(nY);
                    Q.offer(nX);
                }
            }
        }

        copyArr(map, backup);
        return meltCnt;
    }

    static void copyArr(int[][] desc, int[][] src) {
        for (int i = 0; i < desc.length; i++) {
            for (int j = 0; j < desc[0].length; j++) {
                desc[i][j] = src[i][j];
            }
        }
    }
}
