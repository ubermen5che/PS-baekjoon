import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, visitAll;
    static int W[][], Dy[][];
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        visitAll = (1 << N) - 1;
        W = new int[N + 1][N + 1];
        Dy = new int[N + 1][visitAll + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= visitAll; j++) {
                Dy[i][j] = Integer.MAX_VALUE;
            }
        }

        Dy[1][1] = 0;
        getDP(1, 1);
        System.out.println(answer);
    }

    static void getDP(int now, int visited) {
        if (visited == visitAll) {
            if (W[now][1] == 0)
                return;

            answer = Math.min(answer, Dy[now][visited] + W[now][1]);
            return;
        }

        for (int nextCity = 1; nextCity <= N; nextCity++) {
            int nextCityNum = 1 << (nextCity - 1);
            int nextVisited = visited | nextCityNum;

            //이미 방문한 도시는 제외
            if (nextVisited == visited)
                continue;

            //다음 도시로 갈 수 없는 경우
            if (W[now][nextCity] == 0)
                continue;

            //
            if (Dy[nextCity][nextVisited] > Dy[now][visited] + W[now][nextCity]) {
                Dy[nextCity][nextVisited] = Dy[now][visited] + W[now][nextCity];
                getDP(nextCity, nextVisited);
            }
        }
    }
}
