import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, S;
    static int[] nums;
    static int[] maxTree, minTree;
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
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

            int min = query(1, S, 1, a, b, 1);
            int max = query(1, S, 1, a, b, 0);

            sb.append(min + " " + max).append("\n");
        }

        System.out.println(sb);
    }

    static int query(int left, int right, int curNode, int qLeft, int qRight, int type) {
        // maxtree
        if (type == 0) {
            // 범위에서 완전히 벗어난 경우
            if (qRight < left || qLeft > right)
                return Integer.MIN_VALUE;

            // 범위안에 들어오거나 완전히 겹친경우
            if (qLeft <= left && qRight >= right)
                return maxTree[curNode];

            if (qLeft > left || qRight < right) {
                if (left != right) {
                    int mid = (left + right) / 2;
                    int m1 = query(left, mid, curNode * 2, qLeft, qRight, type);
                    int m2 = query(mid + 1, right, curNode * 2 + 1, qLeft, qRight, type);

                    return Math.max(m1, m2);
                }
            }
            return 0;
        } else {
            // 범위에서 완전히 벗어난 경우
            if (qRight < left || qLeft > right)
                return Integer.MAX_VALUE;

            // 범위안에 들어오거나 완전히 겹친경우
            if (qLeft <= left && qRight >= right)
                return minTree[curNode];

            if (qLeft > left || qRight < right) {
                if (left != right) {
                    int mid = (left + right) / 2;
                    int m1 = query(left, mid, curNode * 2, qLeft, qRight, type);
                    int m2 = query(mid + 1, right, curNode * 2 + 1, qLeft, qRight, type);

                    return Math.min(m1, m2);
                }
            }
            return 0;
        }
    }

    static void update(int target, int num, int type) {
        int idx = S + target - 1;
        // maxTree 업데이트
        if (type == 0) {
            maxTree[idx] = num;
            //왼쪽자식인지, 오른쪽 자식인지 확인
            int curNode = idx;
            int parent = idx / 2;
            while(parent >= 1) {
                if (parent == 1) {
                    int maxChild = Math.max(maxTree[parent * 2], maxTree[parent * 2 + 1]);
                    maxTree[parent] = Math.max(maxChild, maxTree[parent]);
                    break;
                }
                if (curNode % 2 == 0) { //왼쪽 자식
                    // 오른쪽 자식과 비교 후 더큰값을 부모로 올림
                    if (maxTree[curNode] > maxTree[curNode + 1]) {
                        maxTree[parent] = maxTree[curNode];
                    } else {
                        maxTree[parent] = maxTree[curNode + 1];
                    }
                } else { //오른쪽 자식
                    if (maxTree[curNode] > maxTree[curNode - 1]) {
                        maxTree[parent] = maxTree[curNode];
                    } else {
                        maxTree[parent] = maxTree[curNode - 1];
                    }
                }
                curNode = parent;
                parent /= 2;
            }
        } else {
            minTree[idx] = num;
            int curNode = idx;
            int parent = idx / 2;
            while(parent >= 1) {
                if (parent == 1) {
                    int minChild = Math.min(minTree[parent * 2], minTree[parent * 2 + 1]);
                    minTree[parent] = Math.min(minChild, minTree[parent]);
                    break;
                }
                if (curNode % 2 == 0) { //왼쪽 자식
                    // 오른쪽 자식과 비교 후 더작은값을 부모로 올림
                    if (minTree[curNode] > minTree[curNode + 1]) {
                        minTree[parent] = minTree[curNode + 1];
                    } else {
                        minTree[parent] = minTree[curNode];
                    }
                } else { //오른쪽 자식
                    if (minTree[curNode] > minTree[curNode - 1]) {
                        minTree[parent] = minTree[curNode - 1];
                    } else {
                        minTree[parent] = minTree[curNode];
                    }
                }
                curNode = parent;
                parent /= 2;
            }
        }
    }
    static void init() {
        for (int i = 1; i <= N; i++) {
            update(i, nums[i], 0);
            update(i, nums[i], 1);
        }
    }

    static void makeTree() {
        S = 2;

        while(N > S) {
            S *= 2;
        }

        maxTree = new int[2 * S];
        minTree = new int[2 * S];

        for(int i = 0; i < 2 * S; i++) {
            maxTree[i] = Integer.MIN_VALUE;
            minTree[i] = Integer.MAX_VALUE;
        }
    }
}
