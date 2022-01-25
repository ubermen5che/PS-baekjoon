import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static Vertex[] graph;
    static int V,E;
    static int start;
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("SDS/secondweek/daytwo/prob1753/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        graph = new Vertex[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new Vertex(i, INF);
        }

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[v1].adjList.add((new Node(v2, w)));
        }

        graph[start].minDistance = 0;

        findShortestPath(start);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (graph[i].minDistance == INF)
                sb.append("INF").append("\n");
            else
                sb.append(graph[i].minDistance).append("\n");
        }

        System.out.print(sb);
    }

    static void findShortestPath(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        graph[start].minDistance = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (graph[curNode.num].minDistance < curNode.distance)
                continue;

            //인접한 노드들에 대해 최소 거리 갱신 작업
            for (Node node : graph[curNode.num].adjList) {
                if (graph[node.num].minDistance > graph[curNode.num].minDistance + node.distance){
                    graph[node.num].minDistance = graph[curNode.num].minDistance + node.distance;
                    pq.offer(new Node(node.num, graph[node.num].minDistance));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int num;
        int distance;

        Node (int num, int weight) {
            this.num = num;
            this.distance = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
    static class Vertex {
        int num;
        int minDistance;
        ArrayList<Node> adjList = new ArrayList<Node>();

        Vertex (int num, int minWeight) {
            this.num = num;
            this.minDistance = minWeight;

        }
    }
}