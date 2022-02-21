import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  문제명 : 거짓말
 *  시간 제한 : 2초
 *  문제 해법 : 주어진 파티 인원수를 토대로 그래프를 만든다. 그 후 진실을 알고있는 사람의 번호를 시작점으로 연결된 노드를 탐색해나간다.
 *           만약 진실을 알고있는 사람과 연결되어있으면 이 사람은 진실을 알게될 것 이고 지민이는 과장을 할수 없다. 각 노드의 방문 상태 정보를 토대로
 *           각 파티에 참가인원을 확인해보면서 방문된 적이 있는 노드에 해당하는 사람이면 해당 파티는 정답 후보에서 제외시킴.
 *  시간 복잡도 : O(N^2)
 *
 */
public class Main {

    static int N, M;
    static ArrayList<Integer>[] graph, parties;
    static ArrayList<Integer> knownPeople = new ArrayList<>();
    static boolean[] visited;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int numOfKnownPeople = Integer.parseInt(st.nextToken());

        // 진실을 아는 사람이 아무도 없는 경우 파티가 열린 횟수가 정답이다.
        if (numOfKnownPeople == 0) {
            System.out.println(M);
            System.exit(0);
        } else {
            for (int i = 0; i < numOfKnownPeople; i++) {
                knownPeople.add(Integer.parseInt(st.nextToken()));
            }

            graph = new ArrayList[N + 1];
            parties = new ArrayList[M + 1];

            for (int i = 0; i <= N; i++)
                graph[i] = new ArrayList<>();

            for (int i = 0; i <= M; i++)
                parties[i] = new ArrayList<>();

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int participants = Integer.parseInt(st.nextToken());
                for (int j = 0; j < participants; j++) {
                    parties[i].add(Integer.parseInt(st.nextToken()));
                }

                // graph 생성
                for (int j = 0; j < parties[i].size() - 1; j++) {
                    int nodeA = parties[i].get(j);
                    for (int k = j + 1; k < parties[i].size(); k++) {
                        int nodeB = parties[i].get(k);
                        graph[nodeA].add(nodeB);
                        graph[nodeB].add(nodeA);
                    }
                }
            }
        }

        // 시작 노드들 기준 bfs search
        visited = new boolean[N + 1];
        bfs();
        // 파티를 연 횟수만큼 반복하면서 파티에 참가안 인원중 전부 not visited라면 answer++;
        for (int i = 1; i <= M; i++) {
            boolean flag = false;
            for (int j = 0; j < parties[i].size(); j++) {
                if (visited[parties[i].get(j)]) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    static void bfs() {
        Queue<Integer> Q = new LinkedList<>();
        for (int i = 0; i < knownPeople.size(); i++) {
            Q.offer(knownPeople.get(i));
            visited[knownPeople.get(i)] = true;
        }

        while (!Q.isEmpty()) {
            Integer now = Q.poll();

            for (Integer next : graph[now]) {
                if (visited[next]) continue;

                visited[next] = true;
                Q.offer(next);
            }
        }
    }
}
