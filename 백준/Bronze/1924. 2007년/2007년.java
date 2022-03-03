import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static int x, y;
    static HashMap<Integer, Integer> monthToDay = new HashMap<>();
    static String[] month = new String[] {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        x = sc.nextInt();
        y = sc.nextInt();

        monthToDay.put(1, 31);
        monthToDay.put(2, 28);
        monthToDay.put(3, 31);
        monthToDay.put(4, 30);
        monthToDay.put(5, 31);
        monthToDay.put(6, 30);
        monthToDay.put(7, 31);
        monthToDay.put(8, 31);
        monthToDay.put(9, 30);
        monthToDay.put(10, 31);
        monthToDay.put(11, 30);
        monthToDay.put(12, 31);

        int sumOfDay = 0;

        for (int i = x - 1; i >= 1; i--) {
            sumOfDay += monthToDay.get(i);
        }

        sumOfDay += y;

        System.out.println(month[sumOfDay % 7]);
    }
}