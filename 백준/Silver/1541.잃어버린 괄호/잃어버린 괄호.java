import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Character> operators = new ArrayList<>();
    static ArrayList<Integer> numbers = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        tokenize(input);
        System.out.println(calculate());
    }

    static int calculate() {
        int sum = numbers.get(0);

        if (operators.size() == 0)
            return sum;

        int numListSize = numbers.size();
        int i = 1;
        int j = 0;

        while (i < numListSize) {
            Character op = operators.get(j++);

            if (op == '+') {
                sum += numbers.get(i++);
            } else {
                // 다음 연산자가 음수 나올때 까지 임시 변수에다가 합을 저장시키고 나중에 한꺼번에 sum에 빼준다.
                // 계속 양수나올경우
                int tmp = numbers.get(i++);
                if (j == operators.size())
                    return sum - tmp;
                // 다음 연산자가 음수일 때 까지 연산자 더해준다
                while (j < operators.size()) {
                    Character operator = operators.get(j++);
                    if (operator == '-') {
                        sum -= tmp;
                        j--;
                        break;
                    } else {
                        if (i + 1 >= numListSize) {
                            tmp += numbers.get(i++);
                            sum -= tmp;
                            break;
                        }
                        tmp += numbers.get(i++);
                    }
                }
            }
        }
        return sum;
    }
    static void tokenize(String input) {
        int len = input.length();
        int i = 0;
        while (i < len) {
            char ch = input.charAt(i);

            //숫자의 경우 연산자 나오기전까지 문자들 합치면서 완성된 숫자 만들기
            if (Character.isDigit(ch)) {
                StringBuilder sb = new StringBuilder();
                sb.append(ch);
                while (true) {
                    // 다음 문자가 연산자의 경우 연산자 list에 넣어주고 i += 2 해주고 break;
                    if (i + 1 < len && !Character.isDigit(input.charAt(i + 1))) {
                        operators.add(input.charAt(i + 1));
                        numbers.add(Integer.parseInt(sb.toString()));
                        i += 2;
                        break;
                    } else {
                        if (i + 1 >= len) {
                            numbers.add(Integer.parseInt(sb.toString()));
                            i++;
                            break;
                        }
                        sb.append(input.charAt(i + 1));
                        i++;
                    }
                }
            }
        }
    }
}
