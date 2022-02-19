import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *  문제명 : A -> B
 *  시간 제한 : 2초
 *  접근법 : A에서 B가 되도록하는 연산의 최소횟수를 계산해야하는 문제. 기본적인 접근으로 완전탐색을 수행해보자.
 *         DFS로 접근시 매 depth마다 2개의 분기가 생성된다. 완전탐색을 마무리 지어야할 시점은 leaf가 B보다 큰 경우일때이다.
 *         숫자 뒤에 1을 붙이는 경우는 자릿수를 하나 늘리게되는데, 이 연산을 9번만 하게 되어도 1111111111이다. 따라서 탐색 수행 속도는
 *         2를 곱하는것 연산에 큰 영향을 받게된다.
 *         트리의 최대 depth는 Leaf가 B보다 큰 시점. leaf의 값이 B보다 크면 짤라낸다.
 *  시간 복잡도 : 1 <= A < B <= 10^9
 *
 */
public class Main {
    static int A, B;
    static int answer = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        StringTokenizer st = new StringTokenizer(input, " ");

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        System.out.println(dfs(A, 1));
    }

    static int dfs(long num, int cnt) {
        if (num > B) {
            return -1;
        }

        if (num == B) {
            return cnt;
        }

        int retA, retB;
        retA = dfs(num * 2, cnt + 1);
        retB = dfs(num * 10 + 1, cnt + 1);

        if (retA == -1 || retB == -1) {
            if (retA == retB)
                return -1;
            else
                return Math.max(retA, retB);
        }
        else
            return Math.min(retA, retB);
    }
}