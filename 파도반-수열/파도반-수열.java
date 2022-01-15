import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());
        long Dy[] = new long[101];

        Dy[1] = 1;
        Dy[2] = 1;
        Dy[3] = 1;

        for (int i = 4; i <= 100; i++)
            Dy[i] = Dy[i-3] + Dy[i-2];

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            sb.append(Dy[Integer.parseInt(bf.readLine())]).append("\n");
        }

        System.out.print(sb);
    }
}