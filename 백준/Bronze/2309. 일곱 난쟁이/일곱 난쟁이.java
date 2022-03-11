import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static int[] dwarfs = new int[9];
    static boolean[] visited = new boolean[9];
    static int[] combDwarf = new int[7];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            dwarfs[i] = Integer.parseInt(bf.readLine());
        }

        comb(0, 0);
    }

    static public void comb(int depth, int idx) {
        if (depth == 7) {
            int sum = 0;

            for (int i = 0; i < 7; i++) {
                sum += combDwarf[i];
            }

            if (sum == 100) {
                Arrays.sort(combDwarf);
                for (int i = 0; i < 7; i++) {
                    System.out.println(combDwarf[i]);
                }
                System.exit(0);
            }
            return;
        }

        for (int i = idx; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combDwarf[depth] = dwarfs[i];
                comb(depth+1, i + 1);
                visited[i] = false;
            }
        }
    }
}