import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static PriorityQueue<Integer>[] distance;
    static int N, M, K;
    static ArrayList<Info>[] map;

    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("SDS/secondweek/daytwo/prob1854/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        distance = new PriorityQueue[N + 1];
        map = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++)
            map[i] = new ArrayList<Info>();

        for (int i = 1; i <= N; i++)
            distance[i] = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            map[from].add(new Info(to, time));
        }

        findShortestPath(1);

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            if (distance[i].size() != K)
                sb.append(-1).append("\n");
            else
                sb.append(distance[i].poll()).append("\n");
        }

        System.out.println(sb);
    }

    static void findShortestPath(int start) {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        distance[start].add(0);
        pq.offer(new Info(start, 0));

        while(!pq.isEmpty()) {
            Info curNode = pq.poll();

            //아래 for문을 보면 K를 기준으로 현재노드와 다음노드의 time을 계속해서 더해가며 갱신하고 있다.
            //만약 이러한 갱신작업이 충분히 진행되었을 때 각 정점의 distance에 존재하는 max heap의 값과
            //시작 노드로부터 현재노드까지 소요되는 시간을 비교해서 현재 노드까지 가는데 걸리는 시간이 더 크게 된다면 더이상
            //distance 갱신작업을 하지않아도 된다. -> K번째 최단 경로시간을 구하기위해 충분히 업데이트 했으므로
            if (curNode.time > distance[curNode.num].peek())
                continue;

            for (Info next : map[curNode.num]) {
                //다음 노드의 pq가 K보다 작다면 아직 K번째 작은 경로에 해당하는 거리값이 갱신되지 않은것.
                if (distance[next.num].size() < K) {
                    distance[next.num].offer(curNode.time + next.time);
                    pq.offer(new Info(next.num, curNode.time + next.time));
                }
                //해당 정점의 pq의 크기가 K보다 큰것은 우리가 찾고자 하는 K번째 최소경로의 거리보다 더 큰 K+n번째 최소경로이므로
                //만약 다음 노드의 distance값이 시작노드로부터 다음노드까지 걸리는 시간보다 크면 max heap에서 하나를 빼주고 더 작은 distance를 max heap에 넣어준다. (K에 도달하도록 조정해주는것)
                else if (distance[next.num].peek() > curNode.time + next.time){
                    distance[next.num].poll();
                    distance[next.num].offer(curNode.time + next.time);
                    pq.offer(new Info(next.num, curNode.time + next.time));
                }
            }
        }
    }

    static class Info implements Comparable<Info>{
        int num;
        int time;

        Info (int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Info o1) {
            return Integer.compare(this.time, o1.time);
        }
    }
}