import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static String P;
    static int K;
    static boolean isNotPrime[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        P = st.nextToken();
        K = Integer.parseInt(st.nextToken());

        makePrime();

        boolean flag = true;

        //k보다 작은 소수들로 나누어 보고 나누어지면 BAD
        for (int i = 2; i < K; i++) {
            if (!isNotPrime[i]) {
                int result = modularBigInteger(i);

                if (result == 0) {
                    System.out.println("BAD" + " " + i);
                    flag = false;
                    break;
                }
            }
        }

        if (flag) {
            System.out.println("GOOD");
        }
    }

    static int modularBigInteger(int num) {
        int r = 0;

        for (int i = 0; i < P.length(); i++) {
            r = (r * 10 + (P.charAt(i) - '0')) % num;
        }

        return r;
    }
    static void makePrime() {
        isNotPrime = new boolean[K+1];

        for (int i = 2; i <= K; i++) {
            if (isNotPrime[i]) continue;
            for (int j = i * 2; j <= K; j += i) {
                isNotPrime[j] = true;
            }
        }
    }
}