import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int dp[][] = new int[201][201]; // N + M을 선택할 수 있는 경우 200
    static int N,M,K;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K > combination(N + M, M)) {//z기준 파스칼 삼각형 도출
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            query(N, M, K, sb);
            System.out.println(sb);
        }
    }

    //n == a갯수, m == z갯수
    public static void query(int n, int m, int k, StringBuilder sb) {
        //단어 끝에 도달 (더이상 고를 수 있는 단어칸이 존재하지 않는다)
        if (n + m == 0) {
            return;
        }
        // a 만 남은경우
        else if (n == 0) {
            sb.append('z');
            query(n, m - 1, k, sb);
        } else if (m == 0) {
            sb.append('a');
            query(n - 1, m , k, sb);
        }
        else {// a,z 둘다 남은 경우 a를 고를 때 조합 수를 보고 판단
            int leftCount = combination(n + m - 1, m);
            if (leftCount >= k) {
                sb.append('a');
                query(n - 1, m, k, sb);
            } else {
                sb.append('z');
                query(n, m - 1, k - leftCount, sb);
            }
        }
    }

    public static int combination(int n, int r) {
        if (n == r || r == 0) {
            return 1;
        }  else if (dp[n][r] != 0) {
            return dp[n][r];
        } else {
            return dp[n][r] = Math.min((int) 1e9, combination(n - 1, r - 1) + combination(n - 1, r));
        }
    }
}