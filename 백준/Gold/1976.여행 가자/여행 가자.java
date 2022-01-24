import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] group;
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("baekjoon/prob1976/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        group = new int[N + 1];

        for (int i = 1; i <= N; i++)
            group[i] = i;

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());
                //union
                if (num == 1) {
                    union(i, j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int sameGroup = 0;
        boolean flag = true;

        for (int i = 0; i < M; i++) {
            int city = Integer.parseInt(st.nextToken());
            if (i == 0)
                sameGroup = find(city);

            int cityGroup = find(city);
            if (sameGroup != cityGroup) {
                flag = false;
                break;
            }
        }

        if (flag)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    static void union(int a, int b) {
        int groupNumA = find(a);
        int groupNumB = find(b);

        if (groupNumA == groupNumB) return;
        else group[groupNumB] = groupNumA;
    }

    static int find(int a) {
        if (group[a] == a) return a;
        else
            return group[a] = find(group[a]);
    }
}