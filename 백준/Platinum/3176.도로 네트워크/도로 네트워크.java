import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] sparseArr;
    static int[][] maxEdge;
    static int[][] minEdge;
    static int[] depth;
    static int N, M, K;
    static int minAnswer, maxAnswer;
    static ArrayList<Info>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        depth = new int[N + 1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        StringTokenizer st;
        for (int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Info(to, dist));
            graph[to].add(new Info(from, dist));
        }

        K = getLogN(N);

        sparseArr = new int[K][N + 1];
        maxEdge = new int[K][N + 1];
        minEdge = new int[K][N + 1];

        M = Integer.parseInt(br.readLine());

        doBfs(1);
        makeSparseArr(K);


        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int city1  = Integer.parseInt(st.nextToken());
            int city2  = Integer.parseInt(st.nextToken());

            getLCA(city1, city2);
            sb.append(minAnswer).append(" ").append(maxAnswer).append("\n");
        }

        System.out.println(sb);
    }

    static int getLCA(int a, int b) {
        //a의 높이가 더 큰것임을 가정
        if (depth[a] < depth[b]) {
            return getLCA(b, a);
        }

        minAnswer = Integer.MAX_VALUE;
        maxAnswer = Integer.MIN_VALUE;

        //높이 맞추기
        //높이를 맞추어 나가면서 도시A와 B사이 경로사이에 존재하는 최장, 최단 도로도 구해주면서 높이올려감.
        //이곳에서 도시A로 가는 경로에 대한 최장, 최소 길이 갱신
        for (int i = 0; i < K; i++) {
            if (((depth[a] - depth[b]) & (1 << i)) >= 1) {
                minAnswer = Math.min(minAnswer, minEdge[i][a]);
                maxAnswer = Math.max(maxAnswer, maxEdge[i][a]);
                a = sparseArr[i][a];
            }
        }

        //높이 맞추었더니 같은 노드인 경우 LCA
        if (a == b) {
            return a;
        }

        //부모노드가 같은 노드일 때 까지 노드를 계속해서 위로올림.
        //최종적으로 부모같아지면 해당노드의 부모가 LCA
        //이곳에서 도시B, A에서 중개점(LCA)전까지 경로의 최단, 최소를 갱신하게된다.
        for (int i = K - 1; i >= 0; i--) {
            if (sparseArr[i][a] != sparseArr[i][b]) {
                minAnswer = Math.min(minAnswer, Math.min(minEdge[i][b], minEdge[i][a]));
                maxAnswer = Math.max(maxAnswer, Math.max(maxEdge[i][b], maxEdge[i][a]));
                a = sparseArr[i][a];
                b = sparseArr[i][b];
            }
        }

        //LCA와 바로 이전 노드들 간의 간선이 최소 또는 최대가 될 수 있기 때문에 아래코드를 추가해준다.
        minAnswer = Math.min(minAnswer, Math.min(minEdge[0][a], minEdge[0][b]));
        maxAnswer = Math.max(maxAnswer, Math.max(maxEdge[0][a], maxEdge[0][b]));

        return sparseArr[0][a];
    }

    static void makeSparseArr(int K) {
        for (int i = 1; i < K; i++) {
            for (int j = 1; j <= N; j++) {
                sparseArr[i][j] = sparseArr[i-1][sparseArr[i-1][j]];
                maxEdge[i][j] = Math.max(maxEdge[i-1][j], maxEdge[i-1][sparseArr[i-1][j]]);
                minEdge[i][j] = Math.min(minEdge[i-1][j], minEdge[i-1][sparseArr[i-1][j]]);
            }
        }
    }

    static void doBfs(int start) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(start);

        depth[start] = 1;

        while(!Q.isEmpty()) {
            Integer now = Q.poll();

            for (Info next : graph[now]) {
                if (depth[next.nodeNum] == 0) {
                    depth[next.nodeNum] = depth[now] + 1;
                    sparseArr[0][next.nodeNum] = now;
                    maxEdge[0][next.nodeNum] = next.dist;
                    minEdge[0][next.nodeNum] = next.dist;
                    Q.offer(next.nodeNum);
                }
            }
        }
    }
    static int getLogN(int N) {
        int num = 1;
        int k = 0;

        while (num < N) {
            num *= 2;
            k++;
        }

        return k;
    }
    static class Info {
        int nodeNum;
        int dist;

        Info (int nodeNum, int dist) {
            this.nodeNum = nodeNum;
            this.dist = dist;
        }
    }
}