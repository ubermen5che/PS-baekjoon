import java.util.Scanner;
import java.util.Stack;

public class Main {

    static int N;
    static boolean[] isNotPrime;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        isNotPrime = new boolean[1111111];
        for (int i = 2; i * i <= 1111110; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * 2; j <= 1111110; j += i)
                    isNotPrime[j] = true;
            }
        }

        if (N == 1) {
            System.out.println(2);
            System.exit(0);
        }
        for (int i = N; i <= 1111110; i++) {
            if (!isNotPrime[i] && isPelin(i)) {
                System.out.println(i);
                System.exit(0);
            }
        }
    }

    static boolean isPelin(int n) {
        String strN = String.valueOf(n);
        Stack stack = new Stack();

        int i;
        for (i = 0; i <= (strN.length() / 2) - 1; i++)
            stack.add((int) strN.charAt(i));

        // n이 홀수인 경우 i++
        if (strN.length() % 2 != 0) {
            i++;
        }

        while (!stack.isEmpty()) {
            if ((int) stack.pop() != (int)strN.charAt(i++)) {
                return false;
            }
        }
        return true;
    }
}