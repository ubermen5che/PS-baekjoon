import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, C;
    static int[] homes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        homes = new int[N + 1];

        for (int i = 1; i <= N; i++)
            homes[i] = Integer.parseInt(br.readLine());

        Arrays.sort(homes, 1, N + 1);

        binarySearch();
    }

    static boolean determination(int D) {
        int cnt = 1;
        int last = homes[1];

        for (int i = 2; i <= N; i++) {
            if (homes[i] - last >= D) {
                cnt++;
                last = homes[i];
            }
        }

        return cnt >= C;
    }

    static void binarySearch() {
        int L = 1, R = 1000000000, ans = 0;

        while (L <= R) {
            int mid = (L + R) / 2;
            if (determination(mid)) {
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        System.out.println(ans);
    }
}
