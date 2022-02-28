import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시간 제한 : 1초
 * 시간 복잡도 : 1 <= N, M <= 8 O(2^N) * O(M) = 256 * 8
 *
 */
public class Main {

    static int N, M;
    static int[] answer, A;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = new int[N];
        A = new int[N];
        visited = new boolean[10001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(A);
        findAnswer(0);
        System.out.println(sb);
    }

    static void findAnswer(int r) {
        if (r == M) {
            for (int i = 0; i < M; i++)
                sb.append(answer[i]).append(" ");
            sb.append("\n");
        } else {
            for (int i = 0; i < N; i++) {
                if (visited[A[i]]) continue;
                if (answer[r] == 0) {
                    answer[r] = A[i];
                    visited[A[i]] = true;
                    findAnswer(r + 1);
                    answer[r] = 0;
                    visited[A[i]] = false;
                }
            }
        }
    }
}