import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  문제명 : 카드 게임 (11062)
 *  시간 제한 : 1초
 *  시간 복잡도 : ?
 *  알고리즘 : DP (그리디 사용하면 최적해 구하지 못함)
 */
public class Main {

    static int T, N;
    static int[] A;
    static int[][] Dy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            A = new int[N + 1];
            Dy = new int[N + 1][N + 1];

            for (int j = 1; j <= N; j++) {
                A[j] = Integer.parseInt(st.nextToken());
            }

            sb.append(solution(1, N, 0)).append("\n");
        }

        System.out.print(sb);
    }

    static int solution(int left, int right, int turn) {
        if (left > right)
            return 0;
        if (Dy[left][right] > 0 ) return Dy[left][right];

        if (turn % 2 == 0)
            return Dy[left][right] = Math.max(solution(left + 1, right, turn + 1) + A[left],
                    solution(left, right - 1, turn - 1) + A[right]);
        else
            return Dy[left][right] = Math.min(solution(left + 1, right, turn + 1),
                    solution(left, right - 1, turn - 1));
    }
}