import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] graph;
    static boolean visited[];
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            graph[nodeA].add(nodeB);
            graph[nodeB].add(nodeA);
        }

        int answer = 0;

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            bfs(i);
            answer++;
        }

        System.out.println(answer);
    }

    static void bfs(int startNode) {
        Queue<Integer> Q = new LinkedList<>();

        ArrayList<Integer> nodeList = graph[startNode];

        for (Integer nodeNum : nodeList) {
            Q.offer(nodeNum);
        }

        while(!Q.isEmpty()) {
            int next = Q.poll();

            ArrayList<Integer> tmp = graph[next];

            for (int i = 0; i < tmp.size(); i++) {
                int nodeNum = tmp.get(i);
                if (visited[nodeNum]) continue;

                visited[tmp.get(i)] = true;
                Q.offer(tmp.get(i));
            }
        }
    }
}