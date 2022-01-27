import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] dy;
    static int W[][];
    static int visitAll;
    static int N, answer;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        visitAll = (1 << N) - 1;

        dy = new int[N + 1][visitAll + 1];
        W = new int[N + 1][N + 1];


        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = INF;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= visitAll; j++) {
                dy[i][j] = INF;
            }
        }

        dy[1][1] = 0;
        getDP(1, 1);
        System.out.println(answer);
    }

    static void getDP(int now, int visited) {
        if (visited == visitAll) {
            if (W[now][1] == 0) {
                return;
            }
            answer = Math.min(answer, dy[now][visited] + W[now][1]);
        }

        //아직 방문할 도시가 남아있는 경우
        for (int nextCityNum = 1; nextCityNum <= N; nextCityNum++) {
            int nextCity = (1<<(nextCityNum-1)); //비트를 하나씩 밀어주면서 다음 도시 위치 계산
            int nextVisited = visited | nextCity;
            //이미 방문했다면 continue
            if (visited == nextVisited)
                continue;

            //다음 도시로가는 길이 없는 경우도 continue
            if (W[now][nextCityNum] == 0)
                continue;

            if (dy[nextCityNum][nextVisited] > dy[now][visited] + W[now][nextCityNum]) {
                dy[nextCityNum][nextVisited] = dy[now][visited] + W[now][nextCityNum];
                getDP(nextCityNum, nextVisited);
            }
        }
    }
}