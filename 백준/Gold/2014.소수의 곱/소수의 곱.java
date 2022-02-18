import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int K, N;
    static long[] primes;
    static PriorityQueue<Long> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        primes = new long[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            primes[i] = Long.parseLong(st.nextToken());
            pq.offer(primes[i]);
        }

        Long ans = 0L;

        for (int i = 0; i < N; i++) {
            ans = pq.poll();
            for (int j = 0; j < K; j++) {
                if (ans * primes[j] >= Integer.MAX_VALUE)
                    break;

                pq.offer(ans * primes[j]);
                if (ans % primes[j] == 0)
                    break;
            }
        }
        System.out.println(ans);
    }
}