import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  문제명 : 기타 레슨
 *  시간 제한 : 2초
 *  문제 유형 : 이분 탐색
 *  시간 복잡도 : 완전탐색시 O(N^2) 이분 탐색시 O(NlogV)
 *
 */
public class Main {

    static int N, M;
    static int[] lessons;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lessons = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            lessons[i] = Integer.parseInt(st.nextToken());
        }

        solution();
    }

    static boolean determination(int V) {
        // 만약 V가 너무 크다면 cnt가 M보다 작을 것이고
        // V가 너무 작다면 cnt가 M보다 클 것 이다.
        // 따라서 cnt > M이면 L = mid + 1;
        // cnt <= M이면 R = mid - 1;
        int cnt = 0;
        int sum = 0;

        for (int i = 1; i <= N; i++) {
            sum += lessons[i];
            // sum이 V보다 커질 때 cnt하는 경우가 발생한다.
            //
            if (sum > V) {
                if (sum - lessons[i] == V) {
                    cnt++;
                    sum = 0;
                } else {
                    if (sum - lessons[i] < V) {
                        cnt++;
                        sum = lessons[i];
                    }
                }
            } else if (sum == V) {
                cnt++;
                sum = 0;
            }
        }

        if (sum > 0) {
            cnt++;
        }

        if (cnt <= M)
            return true;
        else
            return false;
    }
    static void solution() {
        int L = lessons[1], R = 1000000000, ans = 0;

        for (int i = 1; i <= N; i++)
            L = Math.max(L, lessons[i]);

        while(L <= R) {
            int mid = (L + R) / 2;
            if (determination(mid)) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        System.out.println(ans);
    }
}
