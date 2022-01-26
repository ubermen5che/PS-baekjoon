import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *  시간 제한 : 0.5초
 *
 *  시간 복잡도 : O(ElogV) 다익스트라
 *
 *
 */
public class Main {

    static ArrayList<Info>[] graph;
    static int[] distance;
    static int N, M, S, D;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        distance = new int[N + 1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();
        StringTokenizer st;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            graph[from].add(new Info(to, distance));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        findShortestPath(S);
        System.out.println(distance[D]);
    }

    static void findShortestPath(int start) {
        PriorityQueue<Info> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++)
            distance[i] = INF;

        distance[start] = 0;
        pq.offer(new Info(start, 0));

        while(!pq.isEmpty()) {
            Info now = pq.poll();
            
            if (now.distance > distance[now.node])
                continue;
            
            for (Info next : graph[now.node]) {
                
                if (distance[next.node] > distance[now.node] + next.distance) {
                    distance[next.node] = distance[now.node] + next.distance;
                    pq.offer(new Info(next.node, distance[next.node]));
                }
            }
        }
    }

    static class Info implements Comparable<Info>{
        int node;
        int distance;

        Info (int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
}
