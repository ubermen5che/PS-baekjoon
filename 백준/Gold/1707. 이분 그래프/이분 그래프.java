import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int V, E, T;
    static ArrayList<Integer>[] graph;
    static StringBuilder sb = new StringBuilder();
    static int[] color;
    static int RED = 1, BLUE = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            graph = new ArrayList[V + 1];
            for (int j = 1; j <= V; j++)
                graph[j] = new ArrayList<>();

            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a].add(b);
                graph[b].add(a);
            }

            boolean flag = true;

            color = new int[V + 1];

            for (int j = 1; j <= V; j++) {
                if (color[j] == 0) {
                    if (!bfs(j)) {
                        sb.append("NO" + "\n");
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                sb.append("YES"+ "\n");
            }
        }

        System.out.println(sb);
    }

    static boolean bfs(int start) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(start);
        color[start] = RED;

        while(!Q.isEmpty()) {
            Integer now = Q.poll();

            for (Integer next : graph[now]) {
                // 인접한 노드들 중에 현재 노드의 색과 같다면 이분그래프 x
                if (color[now] == color[next]) {
                    return false;
                }

                if (color[next] == 0) {
                    color[next] = color[now] * -1;
                    Q.offer(next);
                }
            }
        }
        return true;
    }
}