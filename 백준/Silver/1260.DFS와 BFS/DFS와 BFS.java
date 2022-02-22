import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, start;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            graph[nodeA].add(nodeB);
            graph[nodeB].add(nodeA);
        }

        //노드 삽입 정보 오름차순으로 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        StringBuilder sb = new StringBuilder();
        dfs(start, sb);
        System.out.println(sb);
        StringBuilder sb2 = new StringBuilder();
        visited = new boolean[N + 1];
        bfs(start, sb2);
        System.out.println(sb2);
    }

    static void bfs(int start, StringBuilder sb) {
        Queue<Integer> Q = new LinkedList<>();

        visited[start] = true;
        Q.offer(start);
        sb.append(start + " ");

        while(!Q.isEmpty()) {
            Integer cur = Q.poll();

            for (int next : graph[cur]) {
                // 인접한 노드들 중에서 아직 방문하지 노드가 있는 경우 정답에 추가해주고 추가 탐색을 위해 큐에 해당 정점번호를 넣어준다.
                if (!visited[next]) {
                    sb.append(next + " ");
                    visited[next] = true;
                    Q.offer(next);
                }
            }
        }
    }
    static void dfs(int cur, StringBuilder sb) {
        // 체크인
        visited[cur] = true;
        sb.append(cur + " ");

        for (int next : graph[cur]) {
            if (!visited[next]) {
                dfs(next, sb);
            }
        }
    }
}