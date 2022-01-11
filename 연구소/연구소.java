import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시간 제한 2초
 * 메모리 제한 512Mb
 *
 * 시간 복잡도
 * 1. (bC3 가지 경우만큼 직접 벽을 세움 (완전 탐색) -> 약 41K번
 * 2. 매번 직접 "탐색"을 통해서 바이러스로부터 안전한 구역 확인하기 O(N^2)
 * 총 시간 복잡도는 O(41K * N^2) = 260만
 *
 * 공간 복잡도 : O(V^2) + O(64C3 * 64)
 *
 * [1~N, 1~M] [1~N, 1~M] [1~N, 1~M]
 */
public class Main {
    static int[][] graph, blank ;
    static boolean visit[][];
    static int N, M, maxArea, B = 0;
    static int[] dx = new int[] {1, 0, -1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maxArea = Integer.MIN_VALUE;
        graph = new int[N+1][M+1];
        blank = new int[N*M+1][2];
        visit = new boolean[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //우선 모든 빈칸에 대한 정보를 입력 (벽의 가능한 위치 조합 생성을 위함)
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (graph[i][j] == 0) {
                    B++;
                    blank[B][0] = i;
                    blank[B][1] = j;
                }
            }
        }

        comb(1, 1);

        System.out.println(maxArea);
    }

    static public void bfs() {
        Queue<Integer> Q = new LinkedList<>();

        //모든 바이러스 위치를 Q에 넣어줌 (MultiSource BFS)
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                visit[i][j] = false;
                if (graph[i][j] == 2) {
                    Q.offer(i);
                    Q.offer(j);
                    visit[i][j] = true;
                }
            }
        }

        while(!Q.isEmpty()) {
            int y = Q.poll();
            int x = Q.poll();   //queue에 행,열 순서로 넣어줬으니 행,열 순서로 빼줌
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx <= 0 || nx > M || ny <= 0 || ny > N) continue;
                if (visit[ny][nx]) continue;
                if (graph[ny][nx] != 0) continue;

                visit[ny][nx] = true;
                Q.offer(ny);
                Q.offer(nx);
            }
        }

        int safeArea = countSafeArea();
        maxArea = Math.max(maxArea, safeArea);
    }

    static public int countSafeArea() {
        int cnt = 0;

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {
                if (!visit[row][col] && graph[row][col] == 0)
                    cnt++;
            }
        }
        return cnt;
    }

    static public void comb(int wallIdx, int k) {
        if (k == 4) {
           bfs();
           return;
        }

        if (wallIdx > B) return;

        graph[blank[wallIdx][0]][blank[wallIdx][1]] = 1;
        comb(wallIdx + 1, k + 1);

        graph[blank[wallIdx][0]][blank[wallIdx][1]] = 0;
        comb(wallIdx + 1, k);
    }
}