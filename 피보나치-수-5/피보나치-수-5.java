import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int Dy[] = new int[21];

        Dy[0] = 0;
        Dy[1] = 1;

        for (int i = 2; i <= 20; i++)
            Dy[i] = Dy[i-1] + Dy[i-2];

        System.out.println(Dy[sc.nextInt()]);
    }
}