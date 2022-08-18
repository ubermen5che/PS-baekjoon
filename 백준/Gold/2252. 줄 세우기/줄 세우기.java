import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] graph;
    static int[] inDegree;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        inDegree = new int[N + 1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            inDegree[to]++;
        }

        topologySort();
        System.out.println(sb);
    }

    static void topologySort() {
        Queue<Integer> Q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0)
                Q.offer(i);
        }

        while(!Q.isEmpty()) {
            int cur = Q.poll();
            sb.append(cur + " ");

            for (int next : graph[cur]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    Q.offer(next);
                }
            }
        }
    }
}