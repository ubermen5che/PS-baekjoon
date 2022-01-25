import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[][] dy;
    static int depth[];
    static ArrayList<Integer>[] map;
    static int N, M;
    static int K = 0;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("SDS/secondweek/daytwo/prob11438/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList();
        }

        //dytable 초기화
        int num = 1;

        while (N > num) {
            num *= 2;
            K++;
        }

        dy = new int[K + 1][N + 1];
        depth = new int [N + 1];
        int a, b;

        //그래프 생성
        for (int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            map[a].add(b);
            map[b].add(a);
        }

        //dfs하면서 dy table 0번째 채워넣기
        doBfs(1);

        makeSparseTable();

        M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            sb.append(getLCA(n1, n2)).append("\n");
        }
        System.out.println(sb);
    }


    public static int getLCA(int a, int b) {
        // a 가 더 깊이 있음을 가정한 로직
        if(depth[a] < depth[b]) {
            return getLCA(b, a);
        }

        // 높이 맞추기
        for(int i = 0 ; i <= K ; i++) {
            if(((depth[a] - depth[b]) & (1 << i)) >= 1) {
                a = dy[i][a];
            }
        }

        // 높이 맞췄으면 같은지 검사
        //a와 b는 노드의 번호를 의미한다. -> 높이를 맞췄더니 노드의 번호가 같으면 공통조상이다.
        if(a == b) {
            return a;
        }

        // 공통조상일 때 까지 부모를 따라 올라간다.
        // 최종적으로는 LCA 바로 밑칸까지만 올라간다.
        for(int i = K; i >= 0 ; i--) {
            if(dy[i][a] != dy[i][b]) { //dy table에 2^0, 2^1, 2^2 ... 2^n승에 해당하는 부모의 노드정보가 이미 저장되어있다.
                a = dy[i][a];
                b = dy[i][b];
            }
        }

        //dy[i][a] == dy[i][b] 인 경우 a와 b 둘중 하나의 바로 위 조상이 공통조상이된다.
        //따라서 dy[0][a]가 공통조상이다.
        return dy[0][a];
    }

    static void makeSparseTable() {
        for (int i = 1; i < K; i++) {
            for (int j = 1; j <= N; j++)
                dy[i][j] = dy[i-1][dy[i-1][j]];
        }
    }
    private static void doBfs(int start) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        depth[start] = 1;
        dq.add(start);

        while(dq.isEmpty() == false) {
            int now = dq.poll();
            for(int next : map[now]) {
                if(depth[next] == 0) { // 탐색하지 않은 지점만 탐색한다.
                    depth[next] = depth[now] + 1;
                    dy[0][next] = now;
                    dq.add(next);
                }
            }
        }
    }
}