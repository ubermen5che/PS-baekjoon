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
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());

        dist = new int[N+1];

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

        bfs(1);

        //dist 배열 쭉 순회하면서 자신의 친구와(dist == 1) 친구의 친구 (dist == 2) cnt
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == 1 || dist[i] == 2)
                answer++;
        }

        System.out.println(answer);
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