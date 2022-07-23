import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {

    static LinkedList<Character> d = new LinkedList<>();
    static int cursor, T;
    static String input;
    // cursor가 answer.size()라면 list가장 마지막에 append
    //
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine();
        T = Integer.parseInt(br.readLine());
        cursor = input.length(); // cursor가 맨 오른쪽에 위치

        for (int i = 0; i < input.length(); i++) {
            d.add(input.charAt(i));
        }

        ListIterator<Character> answer = d.listIterator();

        while(answer.hasNext()) {
            answer.next();
        }

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char op = st.nextToken().charAt(0);

            if (op == 'P') {
                Character c = st.nextToken().charAt(0);
                answer.add(c);
            } else {
                if (op == 'D') {
                    if (answer.hasNext())
                        answer.next();
                } else if (op == 'B') {
                    if (answer.hasPrevious()) {
                        answer.previous();
                        answer.remove();
                    }
                } else {
                    if (answer.hasPrevious())
                        answer.previous();
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (Character ch : d) {
            sb.append(ch);
        }

        System.out.println(sb);
    }
}