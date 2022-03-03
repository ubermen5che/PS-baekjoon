import java.util.Scanner;

public class Main {
    static int N;
    static int[] value = new int[] {500, 100, 50, 10, 5, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        
        int changes = 1000 - N;
        int answer = 0;
        
        for (int i = 0; i < value.length; i++) {
            int Q = changes / value[i];
            answer += Q;
            changes = changes - value[i] * Q;
        }
        
        System.out.println(answer);
    }
}