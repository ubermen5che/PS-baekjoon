import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int selected[];
    static boolean visited[];
    static int N,M;
    static StringBuilder sb = new StringBuilder();;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[N+1];
        visited = new boolean[N+1];
        //
        findAnswer(1);
        System.out.println(sb);
    }

    static public void findAnswer(int k) {
        if (k == M+1) {
            for (int cand = 1; cand <= M; cand++)
                sb.append(selected[cand]).append(" ");
            sb.append("\n");
            return;
        }

        int start = selected[k-1] + 1;
        for (int cand = start; cand <= N; cand++) {
            selected[k] = cand;
            findAnswer(k+1);
            selected[k] = 0;
        }
    }
}