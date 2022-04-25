import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static ArrayList<FireBall> fireBalls = new ArrayList<>();
    static ArrayList<Integer>[][] map = new ArrayList[50][50];
    static int[] dy = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            y--;
            x--;
            fireBalls.add(new FireBall(y, x, m, s, d));
            map[y][x].add(i);
        }

        int ans = solve();
        System.out.println(ans);
    }

    static ArrayList<Integer>[][] move() {
        ArrayList<Integer>[][] newMap = new ArrayList[50][50];

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                newMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < fireBalls.size(); i++) {
            FireBall fireball = fireBalls.get(i);
            int dir = fireball.d;
            int speed = fireball.s % N;
            int nY = (fireball.y + (dy[dir] * speed) + N) % N;
            int nX = (fireball.x + (dx[dir] * speed) + N) % N;

            newMap[nY][nX].add(i);
            fireBalls.get(i).y = nY;
            fireBalls.get(i).x = nX;
        }
        return newMap;
    }

    static ArrayList<FireBall> sum() {
        ArrayList<FireBall> newBall = new ArrayList<>();
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x].size() == 0) continue;
                if (map[y][x].size() == 1) {
                    int index = map[y][x].get(0);
                    newBall.add(fireBalls.get(index));
                    continue;
                }
                int sumM = 0, sumS = 0;
                boolean odd = true, even = true;

                for (int i = 0; i < map[y][x].size(); i++) {
                    int index = map[y][x].get(i);
                    sumM += fireBalls.get(index).m;
                    sumS += fireBalls.get(index).s;
                    if (fireBalls.get(index).d % 2  == 0) odd = false;
                    else even = false;
                }

                if (sumM / 5 == 0) continue;
                int curM = sumM / 5;
                int curS = sumS / map[y][x].size();
                for (int i = 0; i < 4; i++) {
                    if (odd || even) {
                        newBall.add(new FireBall(y, x, curM, curS, i * 2));
                    } else {
                        newBall.add(new FireBall(y, x, curM, curS, i * 2 + 1));
                    }
                }
            }
        }

        return newBall;
    }
    static int solve() {
        while (K-- > 0) {
            map = move();
            fireBalls = sum();
        }
        int res = 0;

        for (int i = 0; i < fireBalls.size(); i++) {
            res += fireBalls.get(i).m;
        }
        return res;
    }

    static class FireBall {
        public FireBall(int y, int x, int m, int s, int d) {
            this.y = y;
            this.x = x;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        int y, x, m, s, d;
    }
}