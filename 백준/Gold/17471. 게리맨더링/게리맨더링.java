import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] graph;
    static int[] population;
    static int[] comb;
    static boolean[] areaInfo;
    static boolean[] visited;
    static int totalPopulation;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = Integer.parseInt(sc.nextLine());
        population = new int[N + 1];
        comb = new int[N + 1];
        graph = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(sc.nextLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            totalPopulation += population[i];
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(sc.nextLine());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                int nodeNum = Integer.parseInt(st.nextToken());
                graph[i][nodeNum] = 1;
            }
        }

        for (int k = 1; k <= N - 1; k++) {
            combination(0, k, 1);
        }

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    static void combination(int depth, int k, int start) {
        if (depth == k) {
            int areaAStartNode = 0;
            int areaBStartNode = 0;

            areaInfo = new boolean[N + 1];
            // 선거구가 모두 연결되어있는지 체크
            for (int i = 0; i < k; i++) {
                areaInfo[comb[i]] = true;
            }

            for (int i = 0; i < k; i++) {
                if (areaInfo[comb[i]])
                    areaAStartNode = comb[i];
            }

            for (int i = 1; i <= N; i++) {
                if (!areaInfo[i]) areaBStartNode = i;
            }

            visited = new boolean[N + 1];
            int resA = bfs(areaAStartNode);
            visited = new boolean[N + 1];
            int resB = bfs(areaBStartNode);

            // 선거구의 지점이 모두 연결되어있는 경우 정답 후보로 쳐줌
            if (resA == k && resB == N - k) {
//                for (int i = 0; i < k; i++) {
//                    System.out.print(comb[i] + " ");
//                }
                int popSum = 0;
                for (int i = 0; i < k; i++) {
                    popSum += population[comb[i]];
                }

//                System.out.println("popSum : " + popSum);
                ans = Math.min(ans, Math.abs(popSum - (totalPopulation - popSum)));
            }
            return;
        }

        for (int cand = start; cand <= N; cand++) {
            comb[depth] = cand;
            combination(depth + 1, k, cand + 1);
            comb[depth] = 0;
        }
    }

    static int bfs(int sNum) {
        int cnt = 1;
        Queue<Integer> Q = new ArrayDeque<>();
        Q.offer(sNum);
        visited[sNum] = true;
        while(!Q.isEmpty()) {
            Integer cur = Q.poll();

            for (int i = 1; i <= N; i++) {
                if (graph[cur][i] == 1 && !visited[i] && areaInfo[cur] == areaInfo[i]) {
                    visited[i] = true;
                    cnt++;
                    Q.offer(i);
                }
            }
        }

        return cnt;
    }
}
