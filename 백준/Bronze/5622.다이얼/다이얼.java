import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static HashMap<Integer, Integer> info = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.next();
        Integer ch = 'A' - 0;
        for (int i = 3; i <= 10; i++) {
            if (i == 8 || i == 10) {
                for (int j = 0; j < 4; j++) {
                    info.put(ch++, i);
                }
            } else {
                for (int j = 0; j < 3; j++) {
                    info.put(ch++, i);
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < input.length(); i++) {
            answer += info.get((int)input.charAt(i));
        }

        System.out.println(answer);
    }
}