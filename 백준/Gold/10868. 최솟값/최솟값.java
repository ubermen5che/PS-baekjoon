import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, S;
    static int[] tree;
    static int[] nums;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        makeTree();
        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int min = query(1, S, 1, a, b);

            sb.append(min).append("\n");
        }

        System.out.println(sb);
    }

    static int query(int left, int right, int curNode, int qLeft, int qRight) {
        // 쿼리가 범위를 완전히 벗어난 경우
        if (left > qRight || right < qLeft)
            return Integer.MAX_VALUE;

        // 쿼리가 범위에 겹치는 경우
        if (left >= qLeft && qRight >= right)
            return tree[curNode];

        if (left < qLeft || qRight < right) {
            if (left != right) {
                int mid = (left + right) / 2;
                int min1 = query(left, mid, curNode * 2, qLeft, qRight);
                int min2 = query(mid + 1, right, curNode * 2 + 1, qLeft, qRight);

                return Math.min(min1, min2);
            }
        }

        return 0;
    }
    static void update(int target, int num) {
        int idx = S + target - 1;

        tree[idx] = num;
        int cur = idx;
        int parent = cur / 2;

        while(parent >= 1) {
            // 왼쪽 자식 노드일 경우
            if (parent == 1) {
                int childMin = Math.min(tree[parent * 2], tree[parent * 2 + 1]);
                tree[parent] = Math.min(tree[parent], childMin);
                break;
            }
            if (cur % 2 == 0) {
                if (tree[cur] > tree[cur + 1]) {
                    tree[parent] = tree[cur + 1];
                } else {
                    tree[parent] = tree[cur];
                }
            }
            // 오른쪽 자식 노드일 경우
            else {
                if (tree[cur] > tree[cur - 1]) {
                    tree[parent] = tree[cur - 1];
                } else {
                    tree[parent] = tree[cur];
                }
            }
            cur = parent;
            parent = parent / 2;
        }
    }

    static void init() {
        for (int i = 1; i <= N; i++) {
            update(i, nums[i]);
        }
    }

    static void makeTree() {
        S = 2;

        while(N > S) {
            S *= 2;
        }
        tree = new int[2 * S];
        for (int i = 0; i < 2 * S; i++)
            tree[i] = Integer.MAX_VALUE;
    }
}