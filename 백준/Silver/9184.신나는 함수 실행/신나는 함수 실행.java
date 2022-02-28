import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  시간 제한 : 1초
 *  문제 접근법 : 단순한 재귀함수 호출을 실행할 경우 O(4^20)정도의 어마어마한 연산을 해야한다. -> Dynamic table에 이전에 연산한 결과를 저장하고 중복호출 시 꺼내다가 쓴다.
 *  시간 복잡도 : O(N^3)
 */
public class Main {

    static int[][][] Dy;
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Dy = new int[21][21][21];

        while (true) {
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1 && c == -1)
                break;

            int result = w(a, b, c);
            sb.append("w(").append(a + ", ").append(b + ", ").append(c + ") ").append("= " + result).append("\n");
            ans.append(sb);
        }

        System.out.println(ans);
    }

    static int w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0)
            return 1;
        else if (a > 20 || b > 20 || c > 20)
            return w(20, 20, 20);
        else if (Dy[a][b][c] != 0)
            return Dy[a][b][c];
        else if (a < b && b < c) {
            Dy[a][b][c-1] = w(a, b, c - 1);
            Dy[a][b - 1][c - 1] = w(a, b - 1, c - 1);
            Dy[a][b - 1][c] = w(a, b - 1, c);
            return Dy[a][b][c - 1] + Dy[a][b - 1][c - 1] - Dy[a][b - 1][c];
        } else {
            Dy[a - 1][b][c] = w(a - 1, b, c);
            Dy[a - 1][b - 1][c] = w(a - 1, b - 1, c);
            Dy[a - 1][b][c - 1] = w(a - 1, b, c - 1);
            Dy[a - 1][b - 1][c - 1] = w(a - 1, b - 1, c - 1);
            return Dy[a - 1][b][c] + Dy[a - 1][b - 1][c] + Dy[a - 1][b][c - 1] - Dy[a - 1][b - 1][c - 1];
        }
    }
}