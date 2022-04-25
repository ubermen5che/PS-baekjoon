import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int cloudSize;
    static Cloud[] cloud = new Cloud[2500];
    static int[][] cloudMap = new int[50][50];
    static int[][] water = new int[50][50];
    static int[] dy = new int[] {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dx = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                water[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        initCloud();
        for (int i = 0; i < M; i++) {
            int d, s;
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            moveCloud(d - 1, s);
            increaseWater();
            deleteCloud();
            copyBug();
            generateCloud();
        }

        int ans = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                ans += water[y][x];
            }
        }

        System.out.println(ans);
    }

    private static void generateCloud() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (water[y][x] >= 2 && cloudMap[y][x] != 1) {
                    createCloud(y, x);
                    water[y][x] -= 2;
                }
            }
        }
    }

    public static void copyBug() {
        int[][] increaseWater = new int[50][50];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (cloudMap[y][x] == 1) {
                    int count = 0;
                    for (int d = 1; d < 8; d += 2) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                        if (water[ny][nx] > 0) count++;
                    }
                    increaseWater[y][x] = count;
                }
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                water[y][x] += increaseWater[y][x];
            }
        }
    }
    private static void deleteCloud() {
        cloudMap = new int[50][50];

        for (int i = 0; i < cloudSize; i++) {
            cloudMap[cloud[i].y][cloud[i].x] = 1;
        }

        cloudSize = 0;
    }

    private static void increaseWater() {
        for (int i = 0; i < cloudSize; i++) {
            water[cloud[i].y][cloud[i].x]++;
        }
    }

    static class Cloud {
        int y, x;
    }

    static void createCloud(int y, int x) {
        cloud[cloudSize].y = y;
        cloud[cloudSize].x = x;
        cloudSize++;
    }

    static void moveCloud(int d, int s) {
        int distY = (dy[d] * s) + (N * 50);
        int distX = (dx[d] * s) + (N * 50);

        for (int i = 0; i < cloudSize; i++) {
            cloud[i].y = (distY + cloud[i].y) % N;
            cloud[i].x = (distX + cloud[i].x) % N;
        }
    }

    static void initCloud() {
        for (int i = 0; i < 2500; i++)
            cloud[i] = new Cloud();

        createCloud(N - 2, 0);
        createCloud(N - 2, 1);
        createCloud(N - 1, 0);
        createCloud(N - 1, 1);
    }
}