import java.util.Scanner;

public class Main {

    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int answer = -1;
        int Q, R;
        int weight = N;
        // case1 : 5kg 봉지로 전부 담을 수 있을 경우 -> best
        if (N % 5 == 0) {
            answer = N / 5;
            System.out.println(answer);
            System.exit(0);
        }
        // case2 : 5Kg봉지와 3Kg봉지 두가지를 이용해서 설탕을 담는 경우 (5Kg봉지를 많이 사용하도록)
        Q = weight / 5;

        // 5Kg 봉지를 하나씩 줄여가면서 최소 봉지 갯수를 구한다.
        int Q2, R2;
        for (int i = Q; i >= 1; i--) {
            // 현재 설탕 무게 기준 3Kg봉지 몇개를 사용해야하는지 구한다.
            int tmp = weight - (i * 5);
            Q2 = tmp / 3;
            R2 = tmp % 3;
            if (R2 == 0) {
                answer = i + Q2;
                System.out.println(answer);
                System.exit(0);
            }
        }

        // case 3 : 3Kg봉지만으로 설탕을 담는 경우
        if (N % 3 == 0) {
            answer = N / 3;
        }

        System.out.println(answer);
    }
}
