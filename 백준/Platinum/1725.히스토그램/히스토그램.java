import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, S;
	static int[] A;
	static long answer = 0;
	static int[] tree;
	static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		A = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		//트리 초기화
		tree = new int[N * 4];
		init(1, N, 1);
		
		System.out.println(getMaxWidth(1, N));
		
	}
	
	static int init(int left, int right, int node) {
		if (left == right) {
			return (int) (tree[node] = left);
		}
		
		int mid = (left + right) / 2;
		int leftMinIdx = init(left, mid, node * 2);
		int rightMinIdx = init(mid + 1, right, node * 2 + 1);
		
		int leftValue = A[leftMinIdx];
		int rightValue = A[rightMinIdx];
		
		
		return tree[node] = leftValue < rightValue ? leftMinIdx : rightMinIdx;
	}
	
	//query는 최소 인덱스를 반환해야함
	static int query(int left, int right, int node, int queryLeft, int queryRight) {
		if (queryLeft > right || left > queryRight)
			return INF;
		
		if (queryLeft <= left && right <= queryRight)
			return tree[node];
		
		int mid = (left + right) / 2;
		int leftMinIdx = query(left, mid, node * 2, queryLeft, queryRight);
		int rightMinIdx = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		
		if (leftMinIdx == INF && rightMinIdx == INF)
			return INF;
		else if (leftMinIdx == INF)
			return rightMinIdx;
		else if (rightMinIdx == INF)
			return leftMinIdx;
		else
			return A[leftMinIdx] < A[rightMinIdx] ? leftMinIdx : rightMinIdx;
	}
	
	static long getMaxWidth(int queryLeft, int queryRight) {
		// left, right 1~N으로 고정, 쿼리만 minIdx에 따라 변경
		int minIdx = query(1, N, 1, queryLeft, queryRight);
		
		long maxWidth = (queryRight - queryLeft + 1) * A[minIdx];
		
		// 왼쪽에 직사각형이 존재하는가?
		// 존재한다면 해당 영역의 직사각형의 최대 넓이와 현재 넓이를 비교해서 더 큰값을 취한다.
		if (queryLeft < minIdx) {
			long tmpWidth = getMaxWidth(queryLeft, minIdx - 1);
			maxWidth = Math.max(maxWidth, tmpWidth);
		}
		
		// midIdx오른쪽 영역에 직사각형이 존재하는가?
		if (queryRight > minIdx) {
			long tmpWidth = getMaxWidth(minIdx + 1, queryRight);
			maxWidth = Math.max(maxWidth, tmpWidth);
		}
		
		return maxWidth;
	}
}
