import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int T;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            Stack stack = new Stack();
            String input = br.readLine();

            for (int j = 0; j < input.length(); j++) {
                // 스택이 비어있는 경우 문자 삽입.
                Character ch = input.charAt(j);
                if (stack.isEmpty()) {
                    stack.push(ch);
                    continue;
                }

                // peek한 값이 자신과 다른 문자이면 스택에서 꺼낸다.
                // case 1 : ( ) -> pop -> 다름
                // case 2 : ( ( -> push -> 같음
                // case 3 : ) ( -> push -> 다름
                // case 4 : ) ) -> push -> 같음
                if (stack.peek().equals('(') && ch.equals(')'))
                    stack.pop();
                // 같은 문자이면 그냥 넣는다.
                else
                {
                    stack.push(ch);
                }
            }

            // 반복문 끝났을 때 스택이 비어있다면 YES 그렇지 않다면 NO
            if (stack.isEmpty())
                sb.append("YES").append("\n");
            else
                sb.append("NO").append("\n");
        }

        System.out.println(sb);
    }
}
