import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int dist[];
    static int answer[];
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N+1];
        answer = new int[N+1];

        graph.add(0, new ArrayList<>());

        for (int i = 1; i <= N; i++)
            graph.add(i, new ArrayList<>());

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(bf.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            graph.get(nodeA).add(nodeB);
            graph.get(nodeB).add(nodeA);
        }

        for (int i = 1; i <= N; i++) {
            //최단거리 dist에 갱신해줌
            bfs(i);
            int sum = 0;
            //갱신된 dist를 기반으로 현재 노드기준 자신과 다른 정점들간의 케빈베이컨 거리의 합을 구함.
            for (int j = 1; j <= N; j++) {
                if (i != j) {
                    sum += dist[j];
                }
            }
            min = Math.min(min, sum);
            answer[i] = sum;
        }

        for (int i = 1; i <= N; i++) {
            if (answer[i] == min) {
                System.out.println(i);
                break;
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> Q = new LinkedList<>();
        ArrayList<Integer> adjNodes = graph.get(start);

        //dist 초기화 작업
        for (int i = 1; i <= N; i++) dist[i] = -1;

        //시작 정점 거리 초기화 및 인접 노드들 dist 갱신
        dist[start] = 0;
        for (Integer i : adjNodes)
            dist[i] = dist[start] + 1;

        Q.addAll(graph.get(start));

        while(!Q.isEmpty()) {
            int curNode = Q.poll();
            adjNodes = graph.get(curNode);

            for (Integer i : adjNodes) {
                if (dist[i] == -1) {//방문 전인 노드라면
                    dist[i] = dist[curNode] + 1;
                    Q.offer(i);
                }
            }
        }
    }
}