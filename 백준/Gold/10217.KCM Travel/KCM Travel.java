import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int T, N, M, K;
	static ArrayList<Info>[] graph;
	static long[][] Dy;
	static int INF = Integer.MAX_VALUE;
	static StringBuilder sb = new StringBuilder();
	static long answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[N + 1];
			Dy = new long[N + 1][M + 1];
			
			for (int j = 1; j <= N; j++)
				graph[j] = new ArrayList<>();
			
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());
				
				graph[from].add(new Info(to, cost, dist));
			}
			
			answer = Integer.MAX_VALUE;
			findShortestPath();
			if (answer == Integer.MAX_VALUE) {
				sb.append("Poor KCM").append("\n");
				continue;
			} else
				sb.append(answer).append("\n");
				
		}
		
		System.out.print(sb);
	}

	static void findShortestPath() {
		PriorityQueue<Info> pq = new PriorityQueue<>();
		
		for (int i = 1; i <= N; i++) {
			Arrays.fill(Dy[i], Integer.MAX_VALUE);
		}
		
		Dy[1][0] = 0;
		
		pq.add(new Info(1, 0, 0));
		
		while (!pq.isEmpty()) {
			Info now = pq.poll();
			
			if (now.num == N) {
				answer = Math.min(answer, now.time);
				break;
			}
				
			for (Info next : graph[now.num]) {
				int sumCost = now.cost + next.cost;
				
				if (sumCost > M)
					continue;
				
				int sumTime = now.time + next.time;
				if (Dy[next.num][sumCost] > sumTime) {
					Dy[next.num][sumCost] = sumTime;
					pq.add(new Info(next.num, sumCost, sumTime));
				}
			}
		}
	}
	static class Info implements Comparable<Info>{
		int num;
		int cost;
		int time;
		
		Info (int num, int cost, int dist) {
			this.num = num;
			this.cost = cost;
			this.time = dist;
		}
		
		@Override
		public int compareTo(Info o) {
			if (this.time == o.time) {
				return this.cost - o.cost;
			} else
				return this.time - o.time;
		}
	}
}
