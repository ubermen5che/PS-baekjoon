import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dx = new int[] {1, 0, 0, -1};
    static int[] dy = new int[] {0, 1, -1, 0};
    static int[][] visited;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        visited = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 1; j <= M; j++) {
                int type = Integer.parseInt(String.valueOf(input.charAt(j-1)));
                map[i][j] = type;
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        System.out.println(bfs(1, 1));
    }

    static int bfs(int startY, int startX) {
        Queue<Node> Q = new LinkedList<>();

        Q.offer(new Node(startX, startY, 1, 0));
        visited[startY][startX] = 0;

        while(!Q.isEmpty()) {
            Node cur = Q.poll();

            if (cur.y == N && cur.x == M) {
                return cur.distance;
            }

            for (int i = 0; i < 4; i++) {
                int nY = cur.y + dy[i];
                int nX = cur.x + dx[i];

                if (nY <= 0 || nY > N || nX <= 0 || nX > M) continue;

                // bfs이므로 (nY, nX)에 도달한 거리는 최단 거리이다.
                // nY, nX에 먼저 도달할 때 두가지 경우가 존재한다.
                // case 1 : 이전에 벽을 부수고 도달한 경우
                    // - 이 경우 이 후에 벽에 도달할 시 이동할 수 없게된다 -> 제외
                // case 2 : 벽을 부수지 않고 도달한 경우
                    // - 이후 벽을 만나도 부수고 이동할 수 있다.
                // 따라서 아래와 같은 조건을 세운다.
                if (visited[nY][nX] > cur.broken) {
                    // 벽이 아닌 경우
                    if (map[nY][nX] == 0) {
                        visited[nY][nX] = cur.broken;
                        Q.offer(new Node(nX, nY, cur.distance + 1, cur.broken));
                    }
                    // 벽인 경우
                    else {
                        if (cur.broken == 0) {
                            Q.offer(new Node(nX, nY, cur.distance + 1, cur.broken + 1));
                            visited[nY][nX] = cur.broken + 1;
                        }
                    }
                }
            }
        }
        return -1;
    }
    static class Node {
        int x, y, distance;
        int broken; // 공사 횟수

        public Node(int x, int y, int distance, int broken) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.broken = broken;
        }
    }
}