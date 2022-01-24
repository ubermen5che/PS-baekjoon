import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static Info[] infoArr;
    static int dy[];

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("SDS/secondweek/dayone/prob1516/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        infoArr = new Info[N + 1];
        dy = new int[N + 1];

        for (int i = 1; i <= N; i++)
            infoArr[i] = new Info(i, 0, 0);

        //topology sort를 위한 그래프 형성
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            infoArr[i].time = time;
            while (true) {
                int input = Integer.parseInt(st.nextToken());

                if (input == -1)
                    break;
                else {
                    infoArr[input].next.add(i);
                    infoArr[i].inDegree++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        topologySort();//topology sort
        for (int i = 1; i <= N; i++)
            sb.append(dy[i]).append("\n");

        System.out.print(sb);
    }

    static void topologySort() {
        Queue<Integer> Q = new LinkedList<>();

        //초기에 indegree가 0인 노드들 queue에 넣어줌.
        for (int i = 1; i <= N; i++) {
            if (infoArr[i].inDegree == 0) {
                Q.offer(infoArr[i].num);
                dy[infoArr[i].num] = infoArr[i].time;
            }
        }

        while (!Q.isEmpty()) {
            Integer curNum = Q.poll();

            ArrayList<Integer> adjList = infoArr[curNum].next;

            if (adjList == null || adjList.isEmpty())
                continue;

            for (Integer i : adjList) {
                infoArr[i].inDegree--;
                //이곳에서 dy에 time값 업데이트 해줌
                dy[infoArr[i].num] = Math.max(dy[infoArr[i].num], dy[curNum] + infoArr[i].time);
                if (infoArr[i].inDegree == 0) {
                    Q.offer(infoArr[i].num);
                }
            }
        }
    }
    static class Info {
        int num;
        int inDegree;
        int time;
        ArrayList<Integer> next = new ArrayList<>();

        Info (int num, int inDegree, int time) {
            this.num = num;
            this.inDegree = inDegree;
            this.time = time;
        }
    }
}