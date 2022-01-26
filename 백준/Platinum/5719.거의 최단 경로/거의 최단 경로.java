import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Info>[] graph;
    static boolean isShortestPath[][];
    static int distance[];
    static ArrayList<Info>[] tracking;
    static int N, M;
    static int S, D;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("SDS/secondweek/daythree/prob5719/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0)
                break;

            graph = new ArrayList[N];
            isShortestPath = new boolean[N][N];
            tracking = new ArrayList[N];
            distance = new int[N];

            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                graph[u].add(new Info(v, t));
            }

            findShortestPath(S);
            if (distance[D] == Integer.MAX_VALUE) {
                sb.append(-1).append("\n");
                continue;
            }
            DFS(D, S);
            findShortestPath(S);

            if (distance[D] == Integer.MAX_VALUE) {
                sb.append(-1).append("\n");
            } else
                sb.append(distance[D]).append("\n");
        }

        System.out.println(sb);
    }

    static void DFS(int now, int end) {
        if (now == end) {
            return;
        }

        for (Info next : tracking[now]) {
            if (!isShortestPath[next.num][now]) {
                isShortestPath[next.num][now] = true;
                DFS(next.num, end);
            }
        }
    }

    static void findShortestPath(int start) {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++)
            tracking[i] = new ArrayList<>();

        //distance배열 초기화
        for (int i = 0; i < N; i++)
            distance[i] = Integer.MAX_VALUE;

        distance[start] = 0;
        pq.offer(new Info(start, 0));
        //tracking[0].add(new Info(start, 0));
        //이 코드는 필요없다 왜냐하면 어짜피 시작노드는 최단 경로 형성에 필요하기때문에
        //아래에 pq에서 뽑힌 다음 자연스럽게 어떤 최단경로의 노드로써 추가될 것이다.
        //만약 위 코드를 작성한다면 시작정점은 다른 어떤 정점의 최단경로가 된다는 의미인데 이는 논리적으로 맞지않다.

        while (!pq.isEmpty()) {
            Info now = pq.poll();

            //만약 우선순위큐에 {A, 10} {A ,15} 가 있다고 가정해보면 먼저 {A, 10}이 꺼내질 것. 그 후 distance배열에 해당 노드를 추가한 거리가 저장될텐데
            //다익스트라에서 이미 한번 최단으로 정해진 노드의 거리 최소값은 변하지 않는다. 따라서 이후에 {A, 15}가 꺼내지고 이곳 조건에 들어오게되면 continue될것.
            if (now.distance > distance[now.num]) {
                continue;
            }

            for (Info next : graph[now.num]) {

                //최단 경로이면 넘어가자
                if (isShortestPath[now.num][next.num] == true)
                    continue;

                //현재경로까지의 최단거리 + 다음노드까지의 거리가 다음 노드가 가지고 있는 최단거리보다 작다면 갱신해줌.
                if (distance[next.num] > distance[now.num] + next.distance && !isShortestPath[now.num][next.num]) {
                    tracking[next.num].clear(); //clear해주는 이유는 최단 경로임을 보장해주기 위함이다.
                    tracking[next.num].add(now); //다음 노드에 최단거리로 도달할 수 있게 해주는 정점을 저장.
                    distance[next.num] = distance[now.num] + next.distance;
                    pq.offer(new Info(next.num, distance[next.num]));
                }
                //최단 경로는 여러개가 있을 수 있다. 이 경우도 고려해주어서 tracking arraylist에 넣어주도록 한다.
                if (distance[next.num] == distance[now.num] + next.distance) {
                    tracking[next.num].add(now);
                }
            }
        }
    }

    static class Info implements Comparable<Info>{
        int num;
        int distance;

        Info (int num, int time) {
            this.num = num;
            this.distance = time;
        }

        @Override
        public int compareTo(Info i) {
            return Integer.compare(this.distance, i.distance);
        }
    }
}