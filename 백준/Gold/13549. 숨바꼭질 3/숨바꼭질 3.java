import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N, K;
    static int INF = Integer.MAX_VALUE;
    static int[] time = new int[100001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        for (int i = 0; i <= 100000; i++)
            time[i] = INF;

        bfs(N);
        System.out.println(time[K]);
    }

    static void bfs(int start) {
        Queue<Integer> Q = new LinkedList<>();

        time[start] = 0;
        Q.offer(start);

        while(!Q.isEmpty()) {
            Integer cur = Q.poll();

            if (cur - 1 >= 0 && time[cur - 1] > time[cur] + 1) {
                time[cur - 1] = time[cur] + 1;
                Q.offer(cur - 1);
            }

            if (cur + 1 <= 100000 && time[cur + 1] > time[cur] + 1) {
                time[cur + 1] = time[cur] + 1;
                Q.offer(cur + 1);
            }

            if (cur * 2 <= 100000 && time[cur * 2] > time[cur]) {
                time[cur * 2] = time[cur];
                Q.offer(cur * 2);
            }
        }
    }
}