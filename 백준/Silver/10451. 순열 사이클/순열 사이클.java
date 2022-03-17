import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int T;
    static int[] sequence;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int answer;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            sequence = new int[N + 1];
            graph = new ArrayList[N + 1];
            visited = new boolean[N + 1];

            for (int j = 1; j <= N; j++)
                graph[j] = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                sequence[j] = Integer.parseInt(st.nextToken());
            }

            // 입력받은 sequence토대로 그래프 형성
            for (int j = 1; j <= N; j++) {
                graph[j].add(sequence[j]);
            }

            // 사이클 검사 (bfs)
            answer = 0;

            for (int j = 1; j <= N; j++) {
                if (!visited[j]) {
                    bfs(j, graph[j].get(0));
                }
            }
            sb.append(answer + "\n");
        }

        System.out.print(sb);
    }

    static void bfs(int start, int nextStart) {
        Queue<Integer> Q = new LinkedList<>();
        visited[nextStart] = true;
        Q.offer(nextStart);

        while(!Q.isEmpty()) {
            Integer cur = Q.poll();

            //System.out.println("cur : " + cur);
            // cur이 시작점으로 돌아온 경우 cycle
            if (cur == start) {
                answer++;
                return;
            }

            for (Integer next : graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    Q.offer(next);
                }
            }
        }
    }
}