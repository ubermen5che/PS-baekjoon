import java.util.Scanner;

public class Main {

    static char[] stack;
    static int top;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        String line = "";
        StringBuilder sb = new StringBuilder();

        while(true) {
            line = sc.nextLine();

            if (line.equals("."))
                break;

            stack = new char[101];
            top = -1;
            for (int i = 0; i < line.length() - 1; i++) {
                char ch = line.charAt(i);
                if (ch == '(' || ch == '[')
                    stack[++top] = ch;
                else {
                    if (top >= 0 && stack[top] == '(' && ch == ')') {
                        stack[top] = '0';
                        top--;
                    } else if (top >= 0 && stack[top] == '[' && ch == ']') {
                        stack[top] = '0';
                        top--;
                    } else {
                        if (ch == ')' || ch == ']') {
                            stack[++top] = ch;
                        }
                    }
                }
            }

            if (top == -1) sb.append("yes").append("\n");
            else if(top >= 0) sb.append("no").append("\n");
        }

        System.out.println(sb);
    }
}
