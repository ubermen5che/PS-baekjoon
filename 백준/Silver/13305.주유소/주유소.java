import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long[] dis, cost;
    static long[] Dy;
    static long minIdx;
    static long minCost = Integer.MAX_VALUE;
    static long disSum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dis = new long[N + 1];
        cost = new long[N + 1];
        Dy = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N; i++)
            dis[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            cost[i] = Integer.parseInt(st.nextToken());

        minIdx = 1;
        minCost = cost[1];
        disSum = dis[1];
        Dy[2] = dis[1] * cost[1];
        for (int i = 3; i <= N; i++) {
            if (minCost > cost[i - 1]) {
                minIdx = i - 1;
                minCost = cost[i - 1];
                disSum = dis[i - 1];
            } else {
                disSum += dis[i - 1];
            }

            Dy[i] = Math.min(Dy[i - 1] + dis[i - 1] * cost[i - 1], Dy[(int)minIdx] + cost[(int)minIdx] * disSum);
        }

        System.out.println(Dy[N]);
    }
}

