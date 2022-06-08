import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static String line;
    static Stack<Character> stack = new Stack<>();
    static Queue<Character> Q = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    static Boolean isTag = false;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        line = sc.nextLine();

        // 문자열의 첫번쨰와 끝은 공백이 아니다.
        // 단어는 숫자와 알파벳 소문자로만 이루어져 있다.
        // 태그는 < 로 시작해서 >로 끝나는 길이가 3이상인 부분 분자열이고 <> 사이에는 알파벳 소문자와 공백만 있다.
        // 태그가 있는경우 큐에 넣고 > 만나면 Q에서 차례대로 뺀 후 정답에 저장.
        // 태그 없는 경우 단어를 stack에 넣고 차례대로 빼면서 정답에 저장, 공백만나면 공백도 포함시킨 후 저장.
        for (int i = 0; i < line.length(); i++) {
            Character ch = line.charAt(i);

            // 괄호의 경우 Q에 넣는다.
            if (ch == '<') {
                if (!stack.isEmpty()) {
                    while(!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                }
                isTag = true;
                Q.offer(ch);
                continue;
            }

            if (ch == '>') {
                isTag = false;
                Q.offer(ch);
                while(!Q.isEmpty()) {
                    sb.append(Q.poll());
                }
                continue;
            }

            if (isTag) {
                Q.offer(ch);
            }

            if (!isTag) {
                if (ch == ' ') {
                    while(!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    sb.append(' ');
                    continue;
                } else {
                    stack.push(ch);
                }
            }
        }

        if (!Q.isEmpty()) {
            while(!Q.isEmpty()) sb.append(Q.poll());
        }

        if (!stack.isEmpty()) {
            while(!stack.isEmpty()) sb.append(stack.pop());
        }

        System.out.println(sb);
    }
}
