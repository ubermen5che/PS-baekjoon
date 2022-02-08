import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static long[][] Dy;
	static int[][] A;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		Dy = new long[N + 1 + 1][N + 1 + 1];
		A = new int[N + 1][2];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i][0] = Integer.parseInt(st.nextToken());
			A[i][1] = Integer.parseInt(st.nextToken());
		}
	
		
		// Dy 채우기
		for (int size = 2; size <= N; size++) {
			for (int s = 1; s <= N - size + 1; s++) {
				int e = s + size - 1;
				Dy[s][e] = Integer.MAX_VALUE;
				for (int k = s; k <= e; k++) {
					Dy[s][e] = Math.min(Dy[s][e], Dy[s][k] + Dy[k + 1][e] + A[s][0] * A[k][1] * A[e][1]);
				}
			}
		}
		System.out.println(Dy[1][N]);
	}
}