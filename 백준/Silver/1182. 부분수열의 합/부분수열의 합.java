import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, S, ans;
	static int[] arr;
	static int[] perm;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0, 0);
		if (S == 0) {
			System.out.println(ans - 1);
		} else
			System.out.println(ans);
	}

	static void dfs(int depth, int sum, int idx) {
		if (depth == N) {
			if (sum == S) ans++;
			return;
		}
		
		dfs(depth + 1, sum + arr[idx], idx + 1); //현재 idx를 더한 경우
		dfs(depth + 1, sum, idx + 1);	//현재 idx를 더하지 않은 경우
	}
}