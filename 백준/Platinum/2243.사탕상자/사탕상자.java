import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N,A,B,C,S;
    static int[] tree;
    static int max = 1000000;
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("SDS/firstweek/dayfour/prob2243/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        S = 1;

        while (S < max) {
            S *= 2;
        }

        tree = new int[2 * S];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            //사탕을 꺼내는 경우 -> 쿼리 Q(맛)
            if (A == 1) {
                B = Integer.parseInt(st.nextToken());
                int idx = query(1, S, 1, B);
                update(1, S, 1, idx, -1);
                sb.append(idx).append("\n");
            }
            //사탕을 넣는 경우 -> 업데이트 U(넣을 사탕의 맛, 사탕 갯수)
            else if (A == 2) {
                B = Integer.parseInt(st.nextToken());
                C = Integer.parseInt(st.nextToken());
                update(1, S,1, B, C);
            }
        }

        System.out.print(sb);
    }

    static int query(int left, int right, int node, int target) {
        if (left == right)
            return left;

        int mid = (left + right) / 2;

        if (tree[node * 2] >= target) { //왼쪽 부터 쿼리 다시 날려준다.
            return query(left, mid, node * 2, target);
        } else { // 오른쪽에 쿼리 날려주는데 이때 tree[leftNode]값을 target에 빼준값을 쿼링한다.
            return query(mid + 1, right, node * 2 + 1, target - tree[node * 2]);
        }
    }
    static void update(int left, int right, int node, int target, int diff) {
        //target이 left right범위 내인 경우
        if (target >= left && target <= right) {
            int mid = (left + right) / 2;
            tree[node] += diff;

            if (left != right) {
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            } else
                return;
        }
    }
}