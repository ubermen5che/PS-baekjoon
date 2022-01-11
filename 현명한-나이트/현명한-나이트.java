import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] dist;
    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N+1][N+1];
        int startY, startX;
        st = new StringTokenizer(bf.readLine());

        startY = Integer.parseInt(st.nextToken());
        startX = Integer.parseInt(st.nextToken());

        bfs(startY, startX);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            sb.append(dist[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]).append(" ");
        }

        System.out.println(sb);
    }

    static void bfs(int y, int x) {
        Queue<Integer> Q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dist[i][j] = -1;
            }
        }

        dist[y][x] = 0;
        Q.offer(y);
        Q.offer(x);

        while(!Q.isEmpty()) {
            int curY = Q.poll(); int curX = Q.poll();

            for (int i = 0; i < 8; i++) {
                int nY = curY + dy[i];
                int nX = curX + dx[i];

                if (nX <= 0 || nX > N || nY <= 0 || nY > N) continue;
                if (dist[nY][nX] != -1) continue;

                dist[nY][nX] = dist[curY][curX] + 1;
                Q.offer(nY);
                Q.offer(nX);
            }
        }
    }
}