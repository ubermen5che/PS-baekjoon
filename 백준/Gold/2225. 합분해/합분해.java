import java.util.Scanner;

public class Main {

    static int N, K;
    static long[][] Dy;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        Dy = new long[N + 1][K + 1];

        System.out.println(dfs(N, 0));
    }

    static Long dfs(int num, int k) {
        if (k > K)
            return 0L;

        if (num == 0 && k == K)
            return 1L;

        if (Dy[num][k] != 0)
            return Dy[num][k];

        for (int i = 0; i <= num; i++) {
            if (k + 1 > K || num - i < 0) continue;
            Dy[num][k] = (Dy[num][k] + dfs(num - i, k + 1)) % 1000000000;
        }

        return Dy[num][k];
    }
}