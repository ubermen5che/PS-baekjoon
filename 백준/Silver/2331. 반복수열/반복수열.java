import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static ArrayList<Long> sequence = new ArrayList<>();
    static HashMap<Long, Integer> history = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int P = sc.nextInt();

        sequence.add((long) A);
        history.put((long) A, 0);
        int idx = 1;

        while (true) {
            String strNum = String.valueOf(sequence.get(sequence.size() - 1));
            Long num = calculate(strNum, P);

            // 이전에 이미 만들어진 수가 존재하기때문에 종료
            if (history.containsKey(num)) {
                idx = history.get(num);
                break;
            }
            history.put(num, idx++);
            sequence.add(num);
        }

        // idx 이전에 생성된 sequence를 정답으로 처리
        System.out.println(idx);
    }

    static Long calculate(String strNum, int P) {
        Long sum = 0L;
        for (int i = 0; i < strNum.length(); i++) {
            int digit = strNum.charAt(i) - '0';
            sum += (long) Math.pow(digit, P);
        }

        return sum;
    }
}