import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] sortArr = new int[10001];
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("baekjoon/prob10989/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            sortArr[Integer.parseInt(br.readLine())]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 10000; i++) {
            if (sortArr[i] == 0)
                continue;
            while (sortArr[i] != 0) {
                sb.append(i).append("\n");
                sortArr[i]--;
            }
        }

        System.out.print(sb);
    }
}