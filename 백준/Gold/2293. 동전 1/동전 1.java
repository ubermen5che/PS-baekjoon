import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] Dy;
    static int[] coins;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Dy = new int[K + 1];
        coins = new int[N];

        for (int i = 0; i < N; i++)
            coins[i] = Integer.parseInt(br.readLine());

        Dy[0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = coins[i]; j <= K; j++) {
                Dy[j] += Dy[j - coins[i]];
            }
        }

        System.out.println(Dy[K]);
    }
}