import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long Dy[] = new long[1000001];

        Dy[1] = 1;
        Dy[2] = 2;
        Dy[3] = 4;

        for (int i = 4; i <= 1000000; i++)
            Dy[i] = (Dy[i-1] + Dy[i-2] + Dy[i-3]) % 1000000009;

        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++)
            sb.append(Dy[sc.nextInt()]).append("\n");

        System.out.print(sb);
    }
}