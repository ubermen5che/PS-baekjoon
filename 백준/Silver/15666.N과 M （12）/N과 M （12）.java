import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] answer, A;
    static StringBuilder sb = new StringBuilder();
    static HashMap<String, String> visited = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = new int[N];
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(A);
        findAnswer(0, 0);
        System.out.println(sb);
    }

    static void findAnswer(int r, int next) {
        if (r == M) {
            StringBuilder checkForVisit = new StringBuilder();
            StringBuilder answerStr = new StringBuilder();
            for (int i = 0; i < M; i++) {
                answerStr.append(answer[i]).append(" ");
                checkForVisit.append(answer[i] + ",");
            }
            if (visited.containsKey(checkForVisit.toString()))
                return;
            else {
                sb.append(answerStr).append("\n");
                visited.put(checkForVisit.toString(), checkForVisit.toString());
            }
        } else {
            for (int i = next; i < N; i++) {
                if (answer[r] == 0) {
                    answer[r] = A[i];
                    findAnswer(r + 1, i);
                    answer[r] = 0;
                }
            }
        }
    }
}