import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent, weightDiff; //weightDiff는
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("SDS/secondweek/daythree/prob3830/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0)
                break;

            parent = new int[N + 1];
            weightDiff = new int[N + 1];

            for (int i = 1; i <= N; i++)
                parent[i] = i;

            String query;
            int a, b, diff;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                query = st.nextToken();

                //union 명령 실행
                if (query.equals("!")) {
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());
                    diff = Integer.parseInt(st.nextToken());

                    union(a, b, diff);
                }
                //find 명령 수행
                else if (query.equals("?")) {
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());

                    if (find(a) == find(b)) {
                        sb.append(weightDiff[b] - weightDiff[a]).append("\n");
                    } else {
                        sb.append("UNKNOWN").append("\n");
                    }
                }
            }
        }
        System.out.print(sb);
    }

    static void union(int a, int b, int diff) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA == parentB)
            return;

        //a에 대해서 b가 얼마나 더 무거운지 저장.
        weightDiff[parentB] = weightDiff[a] - weightDiff[b] + diff;
        //b의 비교기준인 a로 union
        parent[parentB] = parentA;
    }

    static int find(int a) {
        if (parent[a] == a)
            return a;
        else {
            int parentIdx = find(parent[a]);
            //union-find 기본로직에서 경로 압축 로직에 해당.
            //이곳에서는 weight값과 parent 동시에 경로압축을 진행한다.
            weightDiff[a] += weightDiff[parent[a]];
            return parent[a] = parentIdx;
        }
    }
}