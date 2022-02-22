import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(bfs(i, j) + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int bfs(int start, int end) {
        Queue<Integer> Q = new LinkedList<>();

        visited = new boolean[N + 1];
        Q.offer(start);

        while(!Q.isEmpty()) {
            Integer cur = Q.poll();

            for (int next = 1; next < graph[cur].length; next++) {
                if (graph[cur][next] == 1) {
                    if (visited[next]) continue;
                    if (next == end)
                        return 1;
                    visited[next] = true;
                    Q.offer(next);
                }
            }
        }

        return 0;
    }
}