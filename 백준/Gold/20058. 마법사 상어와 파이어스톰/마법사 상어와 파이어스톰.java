import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, Q, arrSize;
    static int[][] map;
    static int[] dy = new int[] {0, 1, 0, -1};
    static int[] dx = new int[] {1, 0, -1, 0};
    static int[] QArr;
    static boolean[][] visit = new boolean[64][64];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        init();

        for (int y = 0; y < arrSize; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < arrSize; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < Q; i++) {
            QArr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < Q; i++) {
            int L = QArr[i];
            if (L > 0) {
                divide(0, 0, arrSize, (1 << L));
            }
            melt();
        }

        int ans = Integer.MIN_VALUE;
        int sumOfIce = 0;

        for (int y = 0; y < arrSize; y++) {
            for (int x = 0; x < arrSize; x++) {
                sumOfIce += map[y][x];
            }
        }

        for (int y = 0; y < arrSize; y++) {
            for (int x = 0; x < arrSize; x++) {
                if (visit[y][x] || map[y][x] <= 0) continue;
                visit[y][x] = true;
                int ret = bfs(y, x);
                ans = Math.max(ans, ret);
            }
        }

        if (ans == Integer.MIN_VALUE)
            ans = 0;

        System.out.println(sumOfIce);
        System.out.println(ans);
    }

    static void melt() {
        int[][] backup = new int[64][64];

        for (int y = 0; y < arrSize; y++) {
            for (int x = 0; x < arrSize; x++) {
                int iceCnt = 0;
                for (int i = 0; i < 4; i++) {
                    int nY = y + dy[i];
                    int nX = x + dx[i];

                    if (nY < 0 || nY >= arrSize || nX < 0 || nX >= arrSize) continue;
                    if (map[nY][nX] <= 0) continue;
                    iceCnt++;
                }

                if (iceCnt >= 3 || map[y][x] == 0) {
                    backup[y][x] = map[y][x];
                } else {
                    backup[y][x] = map[y][x] - 1;
                }
            }
        }

        for (int y = 0; y < arrSize; y++) {
            for (int x = 0; x < arrSize; x++) {
                map[y][x] = backup[y][x];
            }
        }
    }

    static int bfs(int sY, int sX) {
        Queue<Integer> Q = new LinkedList<>();
        int area = 1;
        Q.offer(sY);
        Q.offer(sX);

        while(!Q.isEmpty()) {
            Integer curY = Q.poll();
            Integer curX = Q.poll();

            for (int i = 0; i < 4; i++) {
                int nY = curY + dy[i];
                int nX = curX + dx[i];

                if (nY < 0 || nY >= arrSize || nX < 0 || nX >= arrSize) continue;
                if (visit[nY][nX] || map[nY][nX] <= 0) continue;

                visit[nY][nX] = true;
                area++;
                Q.offer(nY);
                Q.offer(nX);
            }
        }

        return area;
    }
    static void printArr() {
        for (int y = 0; y < arrSize; y++) {
            for (int x = 0; x < arrSize; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void rotate(int sY, int sX, int size) {
        int[][] back = new int[64][64];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                back[sY + x][sX + size - y - 1] = map[sY + y][sX + x];
            }
        }

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                map[sY + y][sX + x] = back[sY + y][sX + x];
            }
        }
    }

    static void divide(int sY, int sX, int len, int size) {
        if (len == size) {
            rotate(sY, sX, size);
            return;
        }

        len /= 2;
        divide(sY, sX, len, size);
        divide(sY + len, sX, len, size);
        divide(sY, sX + len, len, size);
        divide(sY + len, sX + len, len, size);
    }

    static void init() {
        QArr = new int[Q];

        int powerOfN = N;
        arrSize = 1;

        while(powerOfN-- > 0) {
            arrSize *= 2;
        }

        map = new int[arrSize][arrSize];
    }
}
