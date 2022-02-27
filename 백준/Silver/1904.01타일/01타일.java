import java.util.Scanner;

/**
 *  시간 제한 : 0.75초
 *  입력 범위 : 1 <= N <= 1000000
 *  문제 접근법 : 완전탐색 접근시 어마어마한 탐색 경우의 수가 존재한다. O(2^N) 따라서 DP로 접근해볼법 하다. -> O(N)
 */
public class Main {

    static int[] Dy;
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Dy = new int[N + 2];

        Dy[1] = 1;
        Dy[2] = 2;

        for (int i = 3; i <= N; i++) {
            Dy[i] = (Dy[i - 2] + Dy[i - 1]) % 15746;
        }

        System.out.println(Dy[N]);
    }
}