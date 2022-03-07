import java.util.Scanner;

public class Main {

    static String A, B;
    static int[][] Dy;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        A = sc.next();
        B = sc.next();

        Dy = new int[A.length() + 1][B.length() + 1];

        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    Dy[i][j] = Dy[i - 1][j - 1] + 1;
                } else {
                    Dy[i][j] = Math.max(Dy[i][j - 1], Dy[i - 1][j]);
                }
            }
        }

        System.out.println(Dy[A.length()][B.length()]);
    }
}