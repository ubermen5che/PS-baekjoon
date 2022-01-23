import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static LinkedList<Integer> stack = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("SDS/firstweek/daythree/prob10845/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String firstToken = st.nextToken();
            if (firstToken.equals("push")) {
                // 정수 X를 stack에 넣어줌
                int X = Integer.parseInt(st.nextToken());
                stack.offer(X);
            } else if (firstToken.equals("pop")) {
                //스택top에서 정수하나 빼서 출력 비어있는 경우 -1출력
                if (stack.isEmpty())
                    sb.append("-1").append("\n");
                else {
                    sb.append(stack.poll()).append("\n");
                }
            } else if (firstToken.equals("size")) {
                sb.append(stack.size()).append("\n");
            } else if (firstToken.equals("empty")) {
                if (stack.isEmpty())
                    sb.append(1).append("\n");
                else
                    sb.append(0).append("\n");
            } else if (firstToken.equals("front")) {
                if (stack.isEmpty())
                    sb.append(-1).append("\n");
                else
                    sb.append(stack.peek()).append("\n");
            } else if (firstToken.equals("back")) {
                if (stack.isEmpty())
                    sb.append(-1).append("\n");
                else
                    sb.append(stack.peekLast()).append("\n");
            }
        }

        System.out.print(sb);
    }
}