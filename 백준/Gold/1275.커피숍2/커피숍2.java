import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, Q, S;
	static long[] tree;
	static long answer;
	static long[] A;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		A = new long[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++)
			A[i] = Integer.parseInt(st.nextToken());
		
		makeTree();
		initTree();
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			
			if (x > y) {
				int tmp = x;
				x = y;
				y = tmp;
			}
				
			sb.append(query(1, S, 1, x, y)).append("\n");
			update(a, b - A[(int)a]);
			A[a] = b;
		}
		
		System.out.println(sb);
	}

	static long query(int left, int right, int node, int queryLeft, int queryRight) {
		if (queryLeft > right || left > queryRight)
			return 0;
		
		long sum = 0;
		
		if (queryLeft <= left && right <= queryRight)
			return tree[node];
		
		if (queryLeft > left || queryRight < right) {
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
	static void update(int target, long diff) {
		int targetIdx = S + target - 1;
		
		tree[targetIdx] += diff;
		int parent = targetIdx / 2;
		
		while (parent >= 1) {
			tree[parent] += diff;
			parent /= 2;
		}
 	}
	
	static void initTree() {
		for (int i = 1; i <= N; i++) {
			update(i, A[i]);
		}
	}
	static void makeTree() {
		S = 2;
		
		while (N > S) {
			S *= 2;
		}
		
		tree = new long[2 * S];
	}
}