import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static Info[] infoArr; //간선 리스트를 저장하기 위한 배열
    static long[] cost; //일종의 dynamic table이라고 보아도 무방할듯하다.
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        infoArr = new Info[M + 1];
        cost = new long[N + 1]; //시작정점에서 각 정점에 도달하는 최소 비용 저장을 위한 dynamic table

        //초기값 설정
        for (int i = 0; i <= N; i++)
            cost[i] = INF;


        //간선 리스트 생성
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            infoArr[i] = new Info(from, to, T);
        }

        StringBuilder sb = new StringBuilder();

        findShortestPath(1);
        if (isMinusCycle()) {
            sb.append(-1).append("\n");
        } else {
            for (int i = 2; i <= N; i++) {
                if (cost[i] == Integer.MAX_VALUE)
                    sb.append(-1).append("\n");
                else
                    sb.append(cost[i]).append("\n");
            }
        }
        System.out.println(sb);
     }

    //마지막으로 E개의 간선을 반복했을 때 바뀌는 가중치가 존재한다면 음의사이클이 발생한것.
    private static boolean isMinusCycle() {
        for (int i = 1; i <= M; i++) {
            Info nowEdge = infoArr[i];
            if (cost[nowEdge.from] != INF) {
                if (cost[nowEdge.to] > cost[nowEdge.from] + nowEdge.time)
                    return true;
            }
        }
        return false;
    }

    private static void findShortestPath(int start) {
        cost[start] = 0;

        //시작 정점을 제외한 간선들에 대한 최소 비용 계산
        //V-1번 E개의 간선확인시 최단거리를 구할 수 있다.
        for (int i = 1; i <= N - 1; i++) {
            for (int j = 1; j <= M; j++) {
                Info nowEdge = infoArr[j];
                if (cost[nowEdge.from] != INF ) { //갱신
                    if (cost[nowEdge.to] > cost[nowEdge.from] + nowEdge.time) {
                        cost[nowEdge.to] = cost[nowEdge.from] + nowEdge.time;
                    }
                }
            }
        }
    }

    static class Info {
        int from;
        int to;
        int time;

        public Info(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }
}