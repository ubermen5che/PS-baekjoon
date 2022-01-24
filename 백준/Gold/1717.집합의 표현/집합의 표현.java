import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static int[] parent;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("SDS/secondweek/prob1717/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (op == 0) {
                union(a, b);
            } else if (op == 1) {
                if (find(a) == find(b))
                    sb.append("YES").append("\n");
                else
                    sb.append("NO").append("\n");
            }
        }

        System.out.print(sb);
    }

    static void init() {
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
    }

    static void union(int a, int b) {
        int repA = find(a);
        int repB = find(b);

        parent[repB] = repA;
    }
    static int find(int a) {
        if (parent[a] == a) return a; //self-loop라면 대표값 리턴
        else return parent[a] = find(parent[a]);
    }
}
