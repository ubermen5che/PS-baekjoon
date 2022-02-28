import java.util.Scanner;

public class Main {

    static Long[][] Dy;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Dy = new Long[101][10];

        // 초기화 작업
        Dy[1][0] = 0L;
        for (int i = 1; i <= 9; i++)
            Dy[1][i] = 1L;

        // Dynamic Table 채우기
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0)
                    Dy[i][j] = Dy[i - 1][j + 1];
                else if (j == 9)
                    Dy[i][j] = Dy[i - 1][j - 1];
                else
                    Dy[i][j] = (Dy[i - 1][j - 1] + Dy[i - 1][j + 1]) % 1000000000;
            }
        }

        Long answer = 0L;
        for (int i = 0; i <= 9; i++) {
            answer = (answer + Dy[N][i]) % 1000000000;
        }

        System.out.println(answer);
    }
}
