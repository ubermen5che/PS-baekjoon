import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static int N;
    static Info[] infoArr;
    static Stack<Integer> stack = new Stack<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        infoArr = new Info[1000001];
        infoArr[N] = new Info(true, 0);
        bfs(N);

        stack.push(1);
        int cnt = 0;
        int idx = 1;
        while (true) {
            // N이 1인 경우
            if (infoArr[idx].parentIdx == 0)
                break;
            else {
                cnt++;
                stack.push(infoArr[idx].parentIdx);
                idx = infoArr[idx].parentIdx;
            }
        }

        String strCnt = String.valueOf(cnt);
        while(!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }
        System.out.println(strCnt + "\n" + sb);
    }

    static void bfs(int start) {
        Queue<Integer> Q = new LinkedList<>();

        Q.offer(start);

        while(!Q.isEmpty()) {
            Integer cur = Q.poll();

            if (cur == 1)
                break;

            // 3으로 나뉘는 경우
            if (cur % 3 == 0) {
                if (infoArr[cur / 3] == null) {
                    infoArr[cur / 3] = new Info(true, cur);
                    Q.offer(cur / 3);
                }
            }
            // 2로 나뉘는 경우
            if (cur % 2 == 0) {
                if (infoArr[cur / 2] == null) {
                    infoArr[cur / 2] = new Info(true, cur);
                    Q.offer(cur / 2);
                }
            }

            if (cur - 1 > 0) {
                if (infoArr[cur - 1] == null) {
                    infoArr[cur - 1] = new Info(true, cur);
                    Q.offer(cur - 1);
                }
            }
        }
    }
    static class Info {
        boolean visit;
        int parentIdx;

        Info (boolean visit, int parentIdx) {
            this.visit = visit;
            this.parentIdx = parentIdx;
        }
    }
}