import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    static int T;
    static int N;
    static LinkedList<Integer> deque;
    static char[] operators;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("baekjoon/prob5430/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int popDir = 0; // 어디서 값을 뽑아낼지 정하는 변수 0 -> 앞에서 1 -> 뒤에서
            boolean isEmpty = false;

            // [1,2,3,4] split  substring(1, str.length)
            String opStr = br.readLine();
            operators = new char[opStr.length()];

            for (int j = 0; j < operators.length; j++) {
                operators[j] = opStr.charAt(j);
            }

            N = Integer.parseInt(br.readLine());


            deque = new LinkedList<>();

            String arrStr = br.readLine();

            if (N > 0) {
                String[] tokenArr = arrStr.substring(1, arrStr.length() - 1).split(",");
                for (int j = 0; j < tokenArr.length; j++)
                    deque.offer(Integer.parseInt(tokenArr[j]));
            }

            //operator확인하며 deque 뽑아냄
            for (int k = 0; k < operators.length; k++) {
                if (operators[k] == 'R') {
                    if (popDir == 0)
                        popDir = 1;
                    else if (popDir == 1)
                        popDir = 0;
                } else if (operators[k] == 'D') {
                    if (deque.isEmpty()) {
                        sb.append("error").append("\n");
                        isEmpty = true;
                        break;
                    }
                    if (popDir == 0) { //가장 앞에 있는 값을 뺀다.
                        deque.pollFirst();
                    } else
                        deque.pollLast();
                }
            }

            if (!isEmpty) {
                if (popDir == 1) {//deque역순으로 출력
                    sb.append("[");
                    int size = deque.size();

                    if (size == 0) {
                    }
                    else {
                        for (int j = 0; j < size - 1; j++)
                            sb.append(deque.pollLast()).append(",");
                        sb.append(deque.pollLast());
                    }
                    sb.append("]");
                    sb.append("\n");
                } else {
                    sb.append("[");
                    int size = deque.size();
                    if (size == 0) {
                    }
                    else {
                        for (int j = 0; j < size - 1; j++)
                            sb.append(deque.poll()).append(",");
                        sb.append(deque.poll());
                    }
                    sb.append("]");
                    sb.append("\n");
                }
            }
        }
        System.out.print(sb);
    }
}