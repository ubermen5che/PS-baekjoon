import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());

        int Dy[] = new int[N+1];
        int cost[] = new int[N+1];

        for (int i = 1; i <= N; i++)
            cost[i] = Integer.parseInt(st.nextToken());

        Dy[1] = cost[1];

        for (int i = 2; i <= N; i++) {
            //이곳에서 max를 찾아내야하는데...
            int max = Integer.MIN_VALUE;
            int dIdx = i-1;
            for (int j = 1; j <= i; j++) {
                max = Math.max(max, cost[j] + Dy[dIdx]);
                dIdx--;
            }
            Dy[i] = max;
        }

        System.out.println(Dy[N]);
    }
}