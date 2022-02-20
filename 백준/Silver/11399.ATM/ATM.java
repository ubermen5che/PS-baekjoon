import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int answer, N;
    static int[] times;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        times = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(times);

        answer = 0;
        int accSum = 0;
        for (int i = 0; i < N; i++) {
            // answer에는 걸린시간의 누적합이 저장된다.
            // 1 2 3 3 4 -> (0 + 1) + (1 + 2) + ( 3 + 3 ) + (6 + 3) + (9 + 4)
            // answer + times[i]
            answer = answer + times[i];
            accSum += answer;
        }

        System.out.println(accSum);
    }
}