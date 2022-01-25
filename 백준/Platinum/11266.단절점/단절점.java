import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] map;
    static int[] orders, lows;
    static boolean isCutVertices[];
    static int V, E, order;
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("SDS/secondweek/daytwo/prob11266/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        orders = new int[V + 1];
        lows = new int[V + 1];
        isCutVertices = new boolean[V + 1];
        map = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++)
            map[i] = new ArrayList<>();

        int a, b;

        //무향 그래프 이므로 간선 양쪽에 전부 넣어준다.
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            map[a].add(b);
            map[b].add(a);
        }

        order = 0;
        for (int i = 1; i <= V; i++) {
            if (orders[i] == 0)
                dfs(i, true);
        }

        StringBuilder sb = new StringBuilder();

        int cnt = 0;
        for (int i = 1; i <= V; i++) {
            if (isCutVertices[i]) {
                cnt++;
                sb.append(i).append(" ");
            }
        }

        System.out.println(cnt);
        System.out.println(sb);
    }

    static int dfs(int now, boolean isRoot) {
        order++;
        orders[now] = order;
        int rtn = order;
        int child = 0;

        for (int next : map[now]) {
            if (orders[next] == 0) {
                child++;

                int low = dfs(next, false);

                //내 다음 노드들의 order들중에서 가장 작은 order가 현재 order보다 같거나 크면 단절점이다..
                if (isRoot == false && low >= orders[now]) {
                    isCutVertices[now] = true;
                }
                rtn = Math.min(rtn, low);
            } else {
                //이게 뭔 의미지?
                //아... 내 다음 노드들중 가장 작은 order를 얻어내야하니까
                rtn = Math.min(rtn, orders[next]);
            }
        }

        if (isRoot == true && child >= 2) {
            isCutVertices[now] = true;
        }

        return rtn;
    }
}