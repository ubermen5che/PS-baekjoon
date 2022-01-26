import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *  시간 제한 : 1초
 *
 *  시간 복잡도 : O(ElogV) + O(1) X번째 마을에서 각 마을의 최단 경로 + 그 반대의 경로 탐색
 *
 *
 */
public class Main {

    static ArrayList<Info>[] graph;
    static int[] distance, distanceToK;
    static int N, M, X;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        distance = new int[N + 1];
        distanceToK = new int[N + 1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            graph[from].add(new Info(to, distance));
        }

        findShortestPath(X);
        //distance배열에는 X에서 각 도시까지의 최단거리가 저장되어있을것.
        //그렇다면 distance[2]라는 뜻은 X -> 2임 아래에는 2 -> X를 구하면 됨
        int answer = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            distance[i] += findShortestPath(i, X);
            answer = Math.max(answer, distance[i]);
        }

        System.out.println(answer);
    }

    static int findShortestPath(int start, int x) {
        PriorityQueue<Info> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++)
            distanceToK[i] = INF;

        distanceToK[start] = 0;
        pq.offer(new Info(start, 0));

        while(!pq.isEmpty()) {
            Info now = pq.poll();

            if (now.distance > distanceToK[now.node])
                continue;

            for (Info next : graph[now.node]) {

                if (distanceToK[next.node] > distanceToK[now.node] + next.distance) {
                    distanceToK[next.node] = distanceToK[now.node] + next.distance;
                    pq.offer(new Info(next.node, distanceToK[next.node]));
                }
            }
        }
        return distanceToK[x];
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