import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int M;
    static LinkedList<Integer> set = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int x = 0;

            if (op.equals("add")) {
                x = Integer.parseInt(st.nextToken());
                if (!set.contains(x)) {
                    set.add(x);
                }
            } else if (op.equals("remove")) {
                x = Integer.parseInt(st.nextToken());
                if (set.contains(x)) {
                    remove(x);
                }
            } else if (op.equals("check")) {
                x = Integer.parseInt(st.nextToken());
                if (set.contains(x)) {
                    sb.append(1).append("\n");
                }
                else
                    sb.append(0).append("\n");
            } else if (op.equals("toggle")) {
                x = Integer.parseInt(st.nextToken());
                if (set.contains(x)) {
                    remove(x);
                } else {
                    set.add(x);
                }
            } else if (op.equals("all")) {
                set = new LinkedList<>();
                for (int j = 1; j <= 20; j++)
                    set.offer(j);
            } else {
                set = new LinkedList<>();
            }
        }
        System.out.println(sb);
    }

    static void remove(int x) {
        int i = 0;

        while (i < set.size()) {
            if (set.get(i) == x) {
                set.remove(i);
            }
            i++;
        }
    }
}
