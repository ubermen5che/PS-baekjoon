import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int T, V, E, S;
    static ArrayList<Info>[] graph;
    static int[] distance;
    static boolean[] visited;
    static int cnt, INF = Integer.MAX_VALUE;
    static int lastInfection;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());

            graph = new ArrayList[V + 1];
            distance = new int[V + 1];
            visited = new boolean[V + 1];

            cnt = 0;
            for (int j = 1; j <= V; j++)
                graph[j] = new ArrayList<>();

            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                graph[b].add(new Info(a, c));
            }

            findShortestPath();
            // 마지막 컴퓨터가 감염되기까지 걸리는 시간 = distance배열의 최대값(INF 제외)
            int lastInfection = Integer.MIN_VALUE;
            for (int j = 1; j <= V; j++) {
                if (distance[j] == INF) continue;
                if (lastInfection < distance[j])
                    lastInfection = distance[j];
            }
            sb.append(cnt).append(" ").append(lastInfection).append("\n");
        }
        System.out.print(sb);
    }

    static void findShortestPath() {
        PriorityQueue<Info> pq = new PriorityQueue<>();

        // distance 배열 초기화
        for (int i = 1; i <= V; i++)
            distance[i] = INF;

        distance[S] = 0;
        visited[S] = true;
        pq.offer(new Info(S, 0));
        cnt++;

        while(!pq.isEmpty()) {
            Info now = pq.poll();

            if (!visited[now.num]) {
                visited[now.num] = true;
                cnt++;
                lastInfection = now.num;
            }

            for (Info next : graph[now.num]) {

                if (distance[now.num] < now.time)
                    continue;

                if (distance[next.num] > distance[now.num] + next.time) {
                    distance[next.num] = distance[now.num] + next.time;
                    pq.offer(new Info(next.num, distance[next.num]));
                }
            }
        }
    }
    static class Info implements Comparable<Info>{
        int num;
        int time;

        Info(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Info o) {
            return this.time - o.time;
        }
    }
}