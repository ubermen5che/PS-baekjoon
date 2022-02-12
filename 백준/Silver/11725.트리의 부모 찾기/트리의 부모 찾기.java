import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static ArrayList<Integer>[] graph;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }

        bfs(1);

        StringBuilder sb = new StringBuilder();

        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]).append("\n");
        }

        System.out.println(sb);
    }

    static void bfs(int start) {
        Queue<Integer> Q = new LinkedList<>();
        parent[start] = 1;
        Q.offer(start);

        while(!Q.isEmpty()) {
            Integer now = Q.poll();

            for (Integer next : graph[now]) {
                if (parent[next] == 0) {
                    parent[next] = now;
                    Q.offer(next);
                }
            }
        }
    }
}