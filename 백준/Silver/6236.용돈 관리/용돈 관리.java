import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  문제명 : 용돈 관리
 *  시간 제한 : 1초
 *  시간 복잡도 : O(Nlog10000 * 100000)
 *  알고리즘 유형 : 이분 탐색
 */
public class Main {

    static int N, M;
    static int[] money;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        money = new int[N + 1];
        for (int i = 1; i <= N; i++)
            money[i] = Integer.parseInt(br.readLine());

        binarySearch();
    }

    static boolean determination(int cash) {
        int cnt = 1;
        int sum = 0;

        for (int i = 1; i <= N; i++) {
            if (sum + money[i] > cash) {
                cnt++;
                sum = money[i];
            } else {
                sum += money[i];
            }
        }

        return cnt <= M;
    }
    static void binarySearch() {
        int L = money[1], R = 1000000000, ans = 0;

        for (int i = 1; i <= N; i++)
            L = Math.max(L, money[i]);

        while(L <= R) {
            int mid = (L + R) / 2;
            if (determination(mid)) {
                R = mid - 1;
                ans = mid;
            } else {
                L = mid + 1;
            }
        }

        System.out.println(ans);
    }
}