import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static ArrayList<Info>[] graph;
	static boolean[][] visited;
	static int[] distance;
	static int N, M;
	static int V1, V2;
	static int INF = 200000000;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N + 1];
		distance = new int[N + 1];
		visited = new boolean[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++)
			graph[i] = new ArrayList<Info>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Info(to, time));
			graph[to].add(new Info(from, time));
		}
		
		st = new StringTokenizer(br.readLine());
		
		V1 = Integer.parseInt(st.nextToken());
		V2 = Integer.parseInt(st.nextToken());
		
		int[][] result = new int[2][3];
		int v1v2 = 0, v2v1 = 0;
		
		result[0][0] = findShortestPath(1, V1);
		result[0][1] = findShortestPath(V1, V2);
		result[0][2] = findShortestPath(V2, N);
		
		result[1][0] = findShortestPath(1, V2);
		result[1][1] = findShortestPath(V2, V1);
		result[1][2] = findShortestPath(V1, N);
		
		for (int i = 0; i < 3; i++) {
			v1v2 += result[0][i];
			v2v1 += result[1][i];
		}
		
		int answer = Math.min(v1v2, v2v1);
		
		if (answer >= INF)
			System.out.println(-1);
		else
			System.out.println(answer);
	}

	static int findShortestPath(int start, int end) {
		PriorityQueue<Info> pq = new PriorityQueue<>();
		
		for (int i = 1; i <= N; i++)
			distance[i] = INF;
		
		distance[start] = 0;
		pq.offer(new Info(start, 0));
		
		while(!pq.isEmpty()) {
			Info now = pq.poll();
			
			for (Info next : graph[now.num]) {
				//최종 목적지에 도달한 경우
				if (distance[next.num] > distance[now.num] + next.time) {
					distance[next.num] = distance[now.num] + next.time;
					pq.offer(new Info(next.num, next.time));
				}
			}
		}
		
		return distance[end];
	}
	
	static class Info implements Comparable<Info>{
		int num;
		int time;
		
		Info (int to, int time) {
			this.num = to;
			this.time = time;
		}
		
		@Override
		public int compareTo(Info o) {
			return this.time - o.time;
		}
	}
}