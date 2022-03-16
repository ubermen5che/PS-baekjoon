import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    static int N;
    static BigInteger[] factorial;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        factorial = new BigInteger[N + 1];

        calculate(N);
        String num = factorial[N].toString();
        int lastIdx = num.length() - 1;
        int answer = 0;
        while(true) {
            if (num.charAt(lastIdx--) != '0')
                break;
            else
                answer++;
        }

        System.out.println(answer);
    }

    static BigInteger calculate(int N) {
        if (N <= 0)
            return factorial[0] = new BigInteger("1");
        else {
            BigInteger bN = new BigInteger(String.valueOf(N));
            return factorial[N] = bN.multiply(calculate(N - 1));
        }
    }
}