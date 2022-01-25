import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] dy;
    static int[] depth;
    static int N, M, K;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("baekjoon/prob11437/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        depth = new int[N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        makeDyTable();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        depthSearch(1);
        setDyTable();

        M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(LCA(a,b)).append("\n");
        }

        System.out.print(sb);
    }


    static int LCA(int a, int b) {
        if (depth[a] < depth[b])
            return LCA(b, a);

        //높이 맞춰주기
        for (int i = 0; i < K; i++) {
            if (((depth[a] - depth[b]) & (1 << i)) >= 1) {
                a = dy[i][a];
            }
        }

        //높이를 맞췃는데 두 노드가 같은 노드이면 LCA이다.
        if (a == b) {
            return a;
        }

        //최소 공통 조상 나올때 까지 탐색한다.
        for (int i = K - 1; i >= 0; i--) {
            if (dy[i][a] != dy[i][b]) {
                a = dy[i][a];
                b = dy[i][b];
            }
        }

        return dy[0][a];
    }
    static void setDyTable() {
        for (int i = 1; i < K; i++) {
            for (int j = 1; j <= N; j++)
                dy[i][j] = dy[i-1][dy[i-1][j]];
        }
    }
    static void depthSearch(int start) {
        Queue<Integer> Q = new LinkedList<>();

        Q.offer(start);
        depth[start] = 1;
        while(!Q.isEmpty()) {
            int curNode = Q.poll();

            for (Integer i : graph[curNode]) {
                if (depth[i] == 0) {
                    dy[0][i] = curNode;
                    depth[i] = depth[curNode] + 1;
                    Q.offer(i);
                }
            }
        }
    }
    static void makeDyTable() {
        int num = 1;
        while (num < N) {
            K++;
            num *= 2;
        }

        dy = new int[K][N + 1];
    }
}
