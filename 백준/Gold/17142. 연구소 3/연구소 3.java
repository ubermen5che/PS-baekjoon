import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map = new int[50][50];
    static int posSize;
    static Pos[] pos = new Pos[10];
    static int[] dy = new int[] {0, 1, 0, -1};
    static int[] dx = new int[] {1, 0, -1, 0};
    static int[] posComb;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        posComb = new int[M];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 2) {
                    pos[posSize++] = new Pos(y, x, 0);
                }
            }
        }

        dfs(0, 0);
        if (ans == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(ans);
    }

    static int findAnswer() {
        Queue<Pos> Q = new LinkedList<>();

        int spaceCnt = 0;
        boolean visit[][] = new boolean[50][50];
        // 빈공간의 갯수 cnt해줌. 만약 바이러스 퍼진 후 빈공간이 0이라면
        // 최단 시간을 return 아니라면 maxvalue리턴
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 0) spaceCnt++;
            }
        }

        for (int i = 0; i < M; i++) {
            Q.offer(pos[posComb[i]]);
            visit[pos[posComb[i]].y][pos[posComb[i]].x] = true;
        }

        while(!Q.isEmpty()) {
            Pos curPos = Q.poll();

            if (map[curPos.y][curPos.x] == 0) {
                spaceCnt--;
            }

            if (spaceCnt == 0)
                return curPos.time;

            for (int i = 0; i < 4; i++) {
                int nY = curPos.y + dy[i];
                int nX = curPos.x + dx[i];

                if (nX < 0 || nX >= N || nY < 0 || nY >= N) continue;
                if (visit[nY][nX]) continue;
                if (map[nY][nX] == 1) continue;

                visit[nY][nX] = true;
                Q.offer(new Pos(nY, nX, curPos.time + 1));
            }
        }

        return Integer.MAX_VALUE;
    }

    static void dfs(int posCnt, int next) {
        if (posCnt == M) {
            int ret = findAnswer();

            ans = Math.min(ans, ret);
            return;
        }

        for (int cand = next; cand < posSize; cand++) {
            posComb[posCnt] = cand;
            dfs(posCnt + 1, cand + 1);
        }
    }
    static class Pos {
        public Pos(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }

        int y, x, time;
    }
}