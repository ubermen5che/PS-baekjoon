import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int T, M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            PriorityQueue<Integer> minHeap = new PriorityQueue();
            PriorityQueue<Integer> maxHeap = new PriorityQueue(Comparator.reverseOrder());
            M = Integer.parseInt(br.readLine());

            sb.append((M + 1) / 2).append("\n");
            int cnt = 0;

            for (int i = 0; i < M; i++) {
                if (i % 10 == 0) {
                    st = new StringTokenizer(br.readLine());
                }

                int x = Integer.parseInt(st.nextToken());

                if (maxHeap.size() == minHeap.size()) {
                    maxHeap.offer(x);
                } else {
                    minHeap.offer(x);
                }

                // minHeap이 비지 않았다면, maxHeap이 중간값 이하의 값들을 가지도록 해줌.
                if (!minHeap.isEmpty()) {
                    if (maxHeap.peek() > minHeap.peek()) {
                        int t1 = maxHeap.poll();
                        int t2 = minHeap.poll();

                        maxHeap.offer(t2);
                        minHeap.offer(t1);
                    }
                }

                // index는 0부터 시작이므로 i가 짝수값을 가질 때 중간값을 출력해주면됨.
                if (i % 2 == 0) {
                    if (cnt == 9 || i == M - 1) {
                        sb.append(maxHeap.peek());
                        sb.append("\n");
                        cnt = 0;
                    } else {
                        sb.append(maxHeap.peek() + " ");
                    }
                    cnt++;
                }
            }
        }

        System.out.println(sb);
    }
}