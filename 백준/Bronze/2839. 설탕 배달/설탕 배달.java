import java.util.Scanner;

public class Main {

    static int[] dy = new int[5001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int amount = sc.nextInt();

        //init
        dy[3] = 1;
        dy[5] = 1;

        for (int i = 6; i <= 5000; i++) {
            if (dy[i-3] == 0 && dy[i-5] == 0) continue; // 5kg 3kg 둘다 이용해서 담을 수 없는 경우 ex) 4kg
            if (dy[i-3] == 0 && dy[i-5] != 0) dy[i] = dy[i-5] + 1;
            if (dy[i-3] != 0 && dy[i-5] == 0) dy[i] = dy[i-3] + 1;
            if (dy[i-3] != 0 && dy[i-5] != 0) {
                dy[i] = Math.min(dy[i-3], dy[i-5]) + 1;
            }
        }

        if (dy[amount] == 0) {
            System.out.println(-1);
        } else {
            System.out.println(dy[amount]);
        }
    }
}