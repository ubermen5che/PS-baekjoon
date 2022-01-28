import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int N, treeSize, S;
    static long[] answer;
    static int sum = 0;
    static int[] A;
    static HashMap<Integer, Integer> B = new HashMap<>();
    static int[] tree;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {

        init();

        for (int i = 1; i <= N; i++) {
            sum = 0;
            query(1, S, 1, B.get(A[i]) + 1, N);
            answer[i] = sum;
            update(1, S, 1, B.get(A[i]), 1);
        }

        long finalAnswer = 0;
        for (int i = 1; i <= N; i++)
            finalAnswer += answer[i];

        System.out.println(finalAnswer);
    }

    static void update(int left, int right, int node, int target, int diff) {
        //target이 범위밖인 경우
        if (left > target || target > right) {
            return;
        } else {
            //내부 노드인 경우
            if (left != right) {
                tree[node] += diff;
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            } else {
                tree[S + target - 1] = diff;
            }
        }
    }
    static void query(int left, int right, int node, int queryLeft, int queryRight) {
        //query가 범위를 완전히 벗어난 경우
        if (queryRight < left || queryLeft > right) {
            return;
        }

        //query가 범위안에 꽉차게 들어오는 경우 ex) 노드 커버 범위 1-8 쿼리 범위 1-8
        if (queryLeft <= left && queryRight >= right) {
            sum += tree[node];
            return;
        }

        //쿼리가 범위안에 완전히 들어오는 경우 -> 현재 노드가 찾고자하는 정답을 가질 가능성을 내포하지만 정확히 알 수 없음
        //따라서 자식에게 질의를 위임한다.
        if (queryLeft >= left || queryRight <= right) {
            int mid = (left + right) / 2;
            //내부 노드인 경우
            if (left != right) {
                query(left, mid, node * 2, queryLeft, queryRight);
                query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
            } else {
                sum += tree[node];
                return;
            }
        }
    }
    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        answer = new long[N + 1];
        A = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            B.put(Integer.parseInt(st.nextToken()), i);
        }

        setTreeSize();
        tree = new int[treeSize];

    }

    static void setTreeSize() {
        int size = 1;

        while (size < N) {
            size *= 2;
        }

        treeSize = 2 * size ;
        S = size;
    }
}
