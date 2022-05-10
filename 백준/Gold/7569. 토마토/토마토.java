import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int N,M,H;
    static int[][][] map;
    static int[] dx = {0, 0, 0, 0, -1, 1}; //6방향 위, 아래, 앞, 뒤, 좌, 우
    static int[] dy = {0, 0, 1, -1, 0, 0}; //행
    static int[] dz = {-1, 1, 0, 0, 0, 0};
    static int day = 0;
    static LinkedList<int[]> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N][M];

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < M; j++) {
                    map[k][i][j] = Integer.parseInt(st.nextToken());
                    if (map[k][i][j] == 1)
                        queue.offer(new int[]{k, i, j});
                }
            }
        }

        bfs();

    }

    static public void bfs() {
        int[] nodePos;
        int dirX, dirY, dirZ;
        int row, col, height;

        while(!queue.isEmpty()) {
            nodePos = queue.poll();
            row = nodePos[1];
            col = nodePos[2];
            height = nodePos[0];

            day = map[height][row][col];

            for (int i = 0; i < 6; i++) {
                dirX = col + dx[i];
                dirY = row + dy[i];
                dirZ = height + dz[i];

                if (dirX >= 0 && dirX < M && dirY >= 0 && dirY < N && dirZ >= 0 && dirZ < H){
                    if (map[dirZ][dirY][dirX] == 0) {
                        map[dirZ][dirY][dirX] = map[height][row][col] + 1;
                        queue.add(new int[] {dirZ, dirY, dirX});
                    }
                }
            }
        }

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[k][i][j] == 0){
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(day-1);
    }
}