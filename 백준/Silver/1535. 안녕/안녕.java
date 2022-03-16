import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static Integer[][] dp;
    static int[] W; // weight
    static int[] V; // value
    static int N;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        W = new int[N];
        V = new int[N];

        dp = new Integer[N][101];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            W[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(knapsack(N - 1, 99));

    }

    static int knapsack(int i, int k) {
        // i가 0미만, 즉 범위 밖이 된다면
        if (i < 0)
            return 0;

        // 탐색하지 않은 위치라면?
        if (dp[i][k] == null) {

            // 현재 물건(i)을 추가로 못담는 경우 (이전 i값 탐색)
            if(W[i] > k) {
                dp[i][k] = knapsack(i - 1, k);
            }
            // 현재 물건(i)을 담을 수 있는 경우
            else {
                // 이전 i값과 이전 i값에 대한 k-W[i]의 값 + 현재 가치(V[i])중 큰 값을 저장
                dp[i][k] = Math.max(knapsack(i - 1, k), knapsack(i - 1, k - W[i]) + V[i]);
            }
        }
        return dp[i][k];
    }
}