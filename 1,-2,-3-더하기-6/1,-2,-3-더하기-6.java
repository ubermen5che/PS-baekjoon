import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();
        long Dy[] = new long[100001];

        Dy[1] = 1;
        Dy[2] = 2;
        Dy[3] = 2;
        Dy[4] = 3;
        Dy[5] = 3;
        Dy[6] = 6;
        
        for (int i = 7; i <= 100000; i++)
            Dy[i] = (Dy[i-2] + Dy[i-4] + Dy[i-6]) % 1000000009;

        for (int i = 0; i < T; i++)
            sb.append(Dy[sc.nextInt()]).append("\n");

        System.out.print(sb);
    }
}