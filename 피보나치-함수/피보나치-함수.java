import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());
        int Dy[][] = new int[41][2];

        Dy[0][0] = 1;
        Dy[0][1] = 0;
        Dy[1][0] = 0;
        Dy[1][1] = 1;
        Dy[2][0] = 1;
        Dy[2][1] = 1;

        for (int i = 3; i <= 40; i++) {
            Dy[i][0] = Dy[i-1][0] + Dy[i-2][0];
            Dy[i][1] = Dy[i-1][1] + Dy[i-2][1];
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(bf.readLine());
            sb.append(Dy[N][0]).append(" ").append(Dy[N][1]).append("\n");
        }

        System.out.print(sb);
    }
}