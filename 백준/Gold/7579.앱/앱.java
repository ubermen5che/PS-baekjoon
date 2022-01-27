import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] memory;
    static int[] cost;
    static int N, M, totalCost;
    static int[][] dy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memory = new int[N + 1];
        cost = new int[N + 1];
        totalCost = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }

        dy = new int[N + 1][totalCost + 1];

        //Dy[i][j] = i번째 앱까지 종료해나가면서 j의 비용으로 확보할 수 있는 최대메모리
        //Dy[i][j] = Math.max(Dy[i][j], Dy[i-1][j - cost[i]]) + memory[i];
        int answer = totalCost + 1;

        for (int  i = 1; i <= N; i++) {
            for (int j = 0; j <= totalCost; j++) {
                dy[i][j] = Math.max(dy[i][j], dy[i-1][j]);

                if (j - cost[i] >= 0) {
                    dy[i][j] = Math.max(dy[i][j], dy[i-1][j - cost[i]] + memory[i]);
                }
            }
        }

        for (int i = 1; i <= totalCost; i++) {
            if (dy[N][i] >= M) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }
}
