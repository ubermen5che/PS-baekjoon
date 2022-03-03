import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    static int N;
    static Integer[] ropes;
    static int maxW, minW;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        ropes = new Integer[N];

        for (int i = 0; i < N; i++)
            ropes[i] = Integer.parseInt(br.readLine());

        Arrays.sort(ropes, Comparator.reverseOrder());

        int minWPointer = 1;
        maxW = ropes[0];

        while (true) {
            if (minWPointer >= N) break;

            int k = minWPointer - 0 + 1;
            int tmpW = k * ropes[minWPointer];
            if (tmpW > maxW) {
                maxW = tmpW;
            }
            minWPointer++;
        }

        System.out.println(maxW);
    }
}