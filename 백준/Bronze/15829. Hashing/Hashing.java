import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    static int N;
    static BigInteger M = new BigInteger("1234567891");
    static BigInteger r = new BigInteger("31");
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        sc.nextLine();
        String input = sc.nextLine();

        BigInteger sum = new BigInteger("0");

        for (int i = 0; i < input.length(); i++) {
            BigInteger a = new BigInteger(String.valueOf((int)input.charAt(i) - 96));
            sum = sum.add(a.multiply(r.pow(i)));
        }

        System.out.println(sum.mod(M));
    }
}
