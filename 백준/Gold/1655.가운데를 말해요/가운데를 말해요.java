import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            //max heap , min heap 사이즈가 같은 경우 max heap에 우선적으로 값 넣어준다.
            if (maxHeap.size() == minHeap.size())
                maxHeap.offer(Integer.parseInt(br.readLine()));
            else if (maxHeap.size() > minHeap.size()) {//maxHeap size가 더 큰 경우 min heap에 값 넣어줌
                minHeap.offer(Integer.parseInt(br.readLine()));
            }

            //maxHeap의 top은 항상 minHeap 보다 작은 값을 가져야만 올바른 해를 구할 수 있다.
            //만약 외친수의 갯수가 짝수개라면 둘중 작은수를 출력해주어야 하기 때문이다.
            if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                int tmp = maxHeap.poll();
                maxHeap.offer(minHeap.poll());
                minHeap.offer(tmp);
            }

            sb.append(maxHeap.peek()).append("\n");
        }

        System.out.println(sb);
    }
}
