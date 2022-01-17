import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int answer = 0;

        for (int i = 1; i <= N; i++) {
            if (i >= 1 && i <= 99) {//1에서 99까지는 모두다 한수이다.
                answer++;
            } else if (i >= 100 && i <= 999) {
                int[] Q = new int[3];
                int[] R = new int[3];

                Q[0] = i / (int)Math.pow(10, 2);
                R[0] = i % (int)Math.pow(10, 2);

                if (R[0] != 0) {
                    Q[1] = R[0] / (int) Math.pow(10, 1);
                    R[1] = R[0] % (int) Math.pow(10, 1);
                }

                if (R[1] != 0) {
                    Q[2] = R[1] / (int) Math.pow(10, 0);
                    R[2] = R[1] % (int) Math.pow(10, 0);
                }

                //몫의 결과를 기준으로 한수인지 결정
                int tmp = Q[1] - Q[0];

                if (tmp == Q[2] - Q[1])
                    answer++;
            }
        }
        System.out.println(answer);
    }
}