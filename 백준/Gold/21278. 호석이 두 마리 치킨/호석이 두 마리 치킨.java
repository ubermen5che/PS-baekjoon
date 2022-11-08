import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList[] graph;
    static int[] dist;
    static int sumOfDist = Integer.MAX_VALUE;
    static int[] ansVertex = new int[2];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<Integer>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        findAnswer();

        System.out.println(ansVertex[0] + " " + ansVertex[1] + " " + sumOfDist);
    } // End of main

    static void bfs(int s1, int s2) {
        dist = new int[N + 1];

        for (int i = 1; i <= N; i++)
            dist[i] = Integer.MAX_VALUE;

        Queue<Integer> Q = new ArrayDeque<>();

        Q.offer(s1);
        Q.offer(s2);

        dist[s1] = 0;
        dist[s2] = 0;

        while(!Q.isEmpty()) {
            Integer cur = Q.poll();
            ArrayList<Integer> adjList = graph[cur];
            for (Integer next : adjList) {
                // 아직 방문하지 않았다면
                if (dist[next] == Integer.MAX_VALUE) {
                    dist[next] = dist[cur] + 1;
                    Q.offer(next);
                }
            }
        }

        // 현재 시작 정점을 기준 모든 정점까지의 거리를 구함
        int sumDist = 0;
        for (int i = 1; i <= N; i++) {
            sumDist += dist[i] * 2;
        }

        if (sumOfDist >= sumDist) {
            // dist가 같은 경우 건물번호가 작은 값이 더 작은 값이 정답
            if (sumOfDist == sumDist) {
                // 건물 번호 작은값이 s1이랑 같은 경우 건물번호가 큰 값이 더 작은것
                if (ansVertex[0] == s1) {
                    if (ansVertex[1] > s2) {
                        ansVertex[1] = s2;
                    }
                } else if (ansVertex[0] > s1) {
                    ansVertex[0] = s1;
                    ansVertex[1] = s2;
                }
            } else {
                sumOfDist = sumDist;
                ansVertex[0] = s1;
                ansVertex[1] = s2;
            }
        }
    }
    static void findAnswer() {
        // 두 정점의 조합
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                // i, j 두 정점을 시작정점으로 모든 정점까지의 거리를 bfs로 구한다
                bfs(i, j);
            }
        }
    }
}
