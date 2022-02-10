import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N, S;
	static int[] tree;
	static Player[] players;
	static int[] answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		makeTree();
		answer = new int[N + 1];
		players = new Player[N + 1];
		players[0] = new Player(0, 0);
		
		for (int i = 1; i <= N; i++) {
			players[i] = new Player(i, Integer.parseInt(br.readLine()));
		}
		
		Arrays.sort(players);
		
		for (int i = 0; i < N; i++) {
			Player p = players[i];
			
			update(p.idx, 1);
			answer[p.idx] = query(1, S, 1, 1, p.idx);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= N; i++)
			sb.append(answer[i]).append("\n");
		
		System.out.println(sb);
	}

	static int query(int left, int right, int node, int queryLeft, int queryRight) {
		if (left > queryRight || right < queryLeft) {
			return 0;
		}
		
		int sum = 0;
		
		if (queryLeft <= left && right <= queryRight) {
			return tree[node];
		}
		
		if (queryLeft > left || right > queryRight) {
			if (left != right) {
				int mid = (left + right) / 2;
				sum += query(left, mid, node * 2, queryLeft, queryRight);
				sum += query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
			} else {
				return tree[node];
			}
		}
		
		return sum;
	}
	
	static void update(int idx, int value) {
		int treeIdx = S + idx - 1;
		
		tree[treeIdx] += value;		
		int parent = treeIdx / 2;
		
		while (parent >= 1) {
			tree[parent] += value;
			parent /= 2;
		}
	}
	
	static class Player implements Comparable<Player>{
		int idx;
		int speed;
		
		Player(int idx, int speed) {
			this.idx = idx;
			this.speed = speed;
		}
		
		@Override
		public int compareTo(Player o) {
			return o.speed - this.speed;
		}
	}
	
	static void makeTree() {
		S = 2;
		
		while (N > S)
			S *= 2;
		
		tree = new int[2 * S];
	}
}