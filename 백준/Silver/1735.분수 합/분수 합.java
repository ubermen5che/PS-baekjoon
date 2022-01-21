import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] fraction = new int[2][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        fraction[0][0] = Integer.parseInt(st.nextToken()); //첫번째 분수의 분자
        fraction[0][1] = Integer.parseInt(st.nextToken()); //분모

        st = new StringTokenizer(br.readLine());

        fraction[1][0] = Integer.parseInt(st.nextToken());
        fraction[1][1] = Integer.parseInt(st.nextToken());

        //분수의 합 구하기
        int commonMo = fraction[0][1] * fraction[1][1];
        fraction[0][0] *= commonMo / fraction[0][1];
        fraction[1][0] *= commonMo / fraction[1][1];

        //분모를 공통분모로 설정
        fraction[0][1] = commonMo;
        fraction[1][1] = commonMo;

        //분모 더하기
        int A, B;

        A = fraction[0][0] + fraction[1][0];

        //기약 분수인지 확인 -> gcd(fraction[])
        int d = gcd(A,commonMo);

        System.out.println(A / d + " " + commonMo / d);
    }

    static int gcd(int a, int b) {
        int r;

        while (true) {
            r = a % b;
            if (r == 0)
                return b;
            a = b;
            b = r;
        }
    }
}