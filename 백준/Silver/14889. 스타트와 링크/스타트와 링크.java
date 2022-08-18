import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static boolean[] visit;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visit = new boolean[N];


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0);
        System.out.println(min);

    }

    static public void comb(int idx, int depth) {
        //종료조건 : depth가 N / 2일 경우
        if (depth == N / 2) {
            calculateDiff();
            return;
        }

        for (int i = idx; i < N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                comb(i+1, depth+1);
                visit[i] = false;               //dfs끝나면 해당 방문표시 false처리
            }
        }
    }

    static public void calculateDiff() {
        int sumOfSTeamStat = 0;
        int sumOfLTeamStat = 0;

        for (int i = 0; i < N-1; i++) {
            for (int j = i+1; j < N; j++) {
                if (visit[i] && visit[j]) { //i와 j가 모두 true인 경우 두명은 서로 같은 S팀이다.
                    sumOfSTeamStat += map[i][j];
                    sumOfSTeamStat += map[j][i]; //Sij, Sji 더해줌
                } else if (!visit[i] && !visit[j]) { //둘다 방문하지 않은 경우 그 둘은 L팀 멤버이다.
                    sumOfLTeamStat += map[i][j];
                    sumOfLTeamStat += map[j][i];
                }
            }
        }

        int diff = Math.abs(sumOfSTeamStat - sumOfLTeamStat);

        if (diff == 0) {
            System.out.println(0);
            System.exit(0);
        }

        if (diff < min)
            min = diff;
    }
}