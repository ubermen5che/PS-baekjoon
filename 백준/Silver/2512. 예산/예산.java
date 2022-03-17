import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] moneys;
    static int amount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        moneys = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            moneys[i] = Integer.parseInt(st.nextToken());
            if (moneys[i] > right) {
                right = moneys[i];
            }

            if (moneys[i] < left) {
                left = moneys[i];
            }
        }

        amount = Integer.parseInt(br.readLine());
        int answer = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            if (determination(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
                answer = mid;
            }
        }

        if (answer == 0) {
            answer = amount / N;
        }

        System.out.println(answer);
    }

    static boolean determination(int mid) {
        int sum = 0;

        for (int i = 0; i < N; i++) {
            // 요구한 예산이 최대값 후보보다 작은 경우 그냥 해당 예산을 더해준다.
            if (mid > moneys[i]) {
                sum += moneys[i];
            }
            // 요구한 예산이 더 큰 경우 mid값을 sum에 더해줌
            else {
                sum += mid;
            }
        }

        // 이 경우 전체 예산을 초과하는 경우 이므로 right = mid - 1 필요
        if (sum > amount) {
            return true;
        } else
            return false;
    }
}