import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Info>[] graph;
	static ArrayList<Info>[] tracking;
	static int S, D, N, M;
	static int[] distance;
	static int INF = Integer.MAX_VALUE;
	static Stack<Integer> stack = new Stack<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N + 1];
		distance = new int[N + 1];
		tracking = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Info>();
			tracking[i] = new ArrayList<Info>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Info(to, time));
		}
		
		st = new StringTokenizer(br.readLine());
		
		S = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		findShortestPath();
		stack.add(D);
		DFS(D, S);
		sb.append(distance[D]).append("\n");
		sb.append(stack.size()).append("\n");
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		
		System.out.println(sb);
	}
	
	static void DFS(int start, int end) {
		if (start == end) {
			return;
		}
		
		for (Info next : tracking[start]) {
			stack.add(next.to);
			DFS(next.to, end);
		}
	}
	
	static void findShortestPath() {
		PriorityQueue<Info> pq = new PriorityQueue<>();
		
		for (int i = 1; i <= N; i++)
			distance[i] = INF;
		
		distance[S] = 0;
		pq.offer(new Info(S, 0));
		
		while(!pq.isEmpty()) {
			Info now = pq.poll();
			
            if (distance[now.to] < now.time)
                continue;
            
			for (Info next : graph[now.to]) {
				if (distance[next.to] > distance[now.to] + next.time) {
					tracking[next.to].clear();
					tracking[next.to].add(new Info(now.to, next.time)); 
					distance[next.to] = distance[now.to] + next.time;
					pq.offer(new Info(next.to, next.time));
				}
			}
		}
	}
	static class Info implements Comparable<Info>{
		int to;
		int time;
		
		Info (int to, int time) {
			this.to = to;
			this.time = time;
		}
		
		@Override
		public int compareTo(Info o) {
			return this.time - o.time;
		}
	}

}
