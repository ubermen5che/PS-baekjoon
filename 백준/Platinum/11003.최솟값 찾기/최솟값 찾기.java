import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int N, L;
    static int[] A;
    static Deque<Info> deque = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        A = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            int value = A[i];

            if (!deque.isEmpty() && deque.peekFirst().idx < i - L + 1)
                deque.pollFirst();

            while (!deque.isEmpty() && deque.peekLast().value >= value)
                deque.pollLast();

            deque.offer(new Info(value, i));
            sb.append(deque.peekFirst().value + " ");
        }

        System.out.println(sb);
    }

    static class Info {
        int value;
        int idx;

        Info (int value, int idx) {
            this.value = value;
            this.idx = idx;
        }
    }
}