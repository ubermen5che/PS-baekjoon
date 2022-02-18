import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent;
    static ArrayList<Info> infoList = new ArrayList<>();
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];

        for (int i = 1; i <= N; i++)
            parent[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            infoList.add(new Info(from, to, cost));
        }

        Collections.sort(infoList);

        //N - 1개의 간선을 모두 선택할 때 까지 loop
        int cnt = 0;
        int i = 0;
        while (true) {
            Info info = infoList.get(i++);
            int parentA = find(info.from);
            int parentB = find(info.to);

            if (parentA == parentB)
                continue;
            else {
                union(info.from, info.to);
                answer += info.cost;
            }

            cnt++;
            if (cnt == N - 1)
                break;
        }

        System.out.println(answer);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        parent[parentA] = parentB;
    }

    static int find(int a) {
        if (parent[a] == a)
            return a;

        return parent[a] = find(parent[a]);
    }
    static class Info implements Comparable<Info>{
        public Info(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        int from;
        int to;
        int cost;

        @Override
        public int compareTo(Info o) {
            return this.cost - o.cost;
        }
    }
}