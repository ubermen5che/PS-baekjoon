import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int limit[];
    static boolean visited[][][];
    static boolean solution[];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        limit = new int[3];
        visited = new boolean[201][201][201];
        solution = new boolean[201];

        for (int i = 0; i < 3; i++)
            limit[i] = Integer.parseInt(st.nextToken());

        bfs(0, 0, limit[2]);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= limit[2]; i++) {
            if (solution[i] == true)
                sb.append(i).append(" ");
        }

        System.out.println(sb);
    }

    static void bfs(int a, int b, int c) {
        Queue<State> que = new LinkedList<>();

        State st;
        visited[a][b][c] = true;
        que.offer(new State(new int[] {a,b,c}));

        while(!que.isEmpty()) {
            st = que.poll();

            if (st.X[0] == 0) solution[st.X[2]] = true;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == j) continue;
                    State newState = st.move(i, j, limit);

                    if (visited[newState.X[0]][newState.X[1]][newState.X[2]])
                        continue;
                    que.offer(newState);
                    visited[newState.X[0]][newState.X[1]][newState.X[2]] = true;
                }
            }
        }
    }
    static class State {
        int[] X;
        public State(int[] _X) {
            X = new int[3];

            for (int i = 0; i < 3; i++)
                X[i] = _X[i];
        }

        public State move(int from, int to, int[] Limit) {
            int[] nX = new int[] {X[0], X[1], X[2]};
            //from에서 to로 물 이동시 to가 가득차는 경우
            if (X[from] + X[to] >= Limit[to]) {
                nX[from] -= Limit[to] - X[to];
                nX[to] = Limit[to];
            } else {
                nX[to] = nX[from] + nX[to];
                nX[from] = 0;
            }
            return new State(nX);
        }
    }
}