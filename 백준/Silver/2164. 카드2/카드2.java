import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static Queue<Integer> Q = new LinkedList<>();
    static boolean flag = false;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        for (int i = 1; i <= N; i++) {
            Q.offer(i);
        }

        while(Q.size() > 1) {
            // 버린다
            if (!flag) {
                Q.poll();
                flag = true;
            } else {
                Q.offer(Q.poll());
                flag = false;
            }
        }

        System.out.println(Q.poll());
    }
}