import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int map[][];
    static int N, M;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("SDS/secondweek/daytwo/prob11404/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        M = Integer.parseInt(br.readLine());
        int a, b, c;
        for(int i = 1 ; i <= M ;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if(map[a][b] == 0 || map[a][b] > c) {
                map[a][b] = c;
            }
        }

        for(int k = 1 ; k <= N ;k++) {
            for(int i = 1 ; i <= N ;i++) {
                for(int j = 1 ; j <= N ;j++) {
                    if(i != j && map[i][k] != 0 && map[k][j] != 0) {
                        if(map[i][j] == 0 || map[i][j] > map[i][k] + map[k][j]) {
                            map[i][j] = map[i][k] + map[k][j];
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}