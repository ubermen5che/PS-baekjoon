import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static ArrayList<Edge> edgeList = new ArrayList<>();
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];

        for (int i = 1; i <= V; i++)
            parent[i] = i;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(nodeA, nodeB, cost));
        }

        Collections.sort(edgeList);

        int answer = 0;
        int edgeCnt = 0;
        int i = 0;
        while (true) {
            Edge edge = edgeList.get(i++);
            int parentA = find(edge.nodeA);
            int parentB = find(edge.nodeB);

            if (parentA != parentB) {
                answer += edge.cost;
                // 두 노드의 부모가 서로 다르니까 MST를 구성하는 노드 집합에 포함 시켜준다.
                union(edge.nodeA, edge.nodeB);
                edgeCnt++;
            }

            if (edgeCnt == V - 1)
                break;
        }

        System.out.println(answer);
    }

    static void union(int nodeA, int nodeB) {
        int parentA = find(nodeA);
        int parentB = find(nodeB);

        if (parentA == parentB)
            return;

        parent[parentA] = parentB;
    }

    static int find(int node) {
        if (parent[node] == node)
            return node;

        return parent[node] = find(parent[node]);
    }
    static class Edge implements Comparable<Edge>{
        int nodeA;
        int nodeB;
        int cost;

        Edge (int nodeA, int nodeB, int cost) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
