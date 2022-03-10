import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int N;
    static PriorityQueue<Info> pq;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>((o1, o2) -> {
            // 절대값 같으면 음수부터 꺼냄.
            if (o1.absVal == o2.absVal) {
                if (o1.sign < o2.sign)
                    return -1;
                else if (o1.sign > o2.sign) {
                    return 1;
                } else
                    return 0;
            }
            // 절대값이 같지 않으면 절대값 기준 오름차순
            else {
                return Integer.compare(o1.absVal, o2.absVal);
            }
        });

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            // pq에서 원소 뽑아서 정답에 더해줌.
            if (x == 0) {
                if (pq.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    Info info = pq.poll();
                    sb.append(info.absVal * info.sign).append("\n");
                }
            }
            // pq에 원소 넣어줌.
            else {
                // case 1 : x가 양수일 경우
                if (x > 0 ) {
                    pq.offer(new Info(x, 1));
                }
                // case 2 : x가 음수일 경우
                else {
                    pq.offer(new Info(x * -1, -1));
                }
            }
        }

        System.out.print(sb);
    }

    static class Info {
        int absVal;
        int sign;

        Info (int absVal, int sign) {
            this.absVal = absVal;
            this.sign = sign;
        }
    }
}