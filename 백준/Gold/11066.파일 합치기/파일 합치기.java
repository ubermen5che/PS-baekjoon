import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int T, K;
	static int[][] Dy, sum;
	static int[] A;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			K = Integer.parseInt(br.readLine());
			A = new int[K + 1];
			Dy = new int[K + 1][K + 1];
			sum = new int[K + 1][K + 1];
			
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= K; j++)
				A[j] = Integer.parseInt(st.nextToken());
			
			// sum 배열 값 채워넣기
			for (int j = 1; j <= K; j++) {
				for (int k = j; k <= K; k++) {
					sum[j][k] = sum[j][k-1] + A[k];
				}
			}
			
			// 초기값 셋팅
			for (int j = 1; j <= K; j++) {
				Dy[j][j] = 0;
			}
			
			// Dynamic table 채워넣기
			for (int len = 2; len <= K; len++) {
				for (int i = 1; i <= K - len + 1; i++) {
					int j = i + len - 1;
					Dy[i][j] = Integer.MAX_VALUE;
					for (int k = i; k < j; k++) {
						Dy[i][j] = Math.min(Dy[i][j], Dy[i][k] + Dy[k + 1][j] + sum[i][j]);
					}
				}
			}
			sb.append(Dy[1][K]).append("\n");
		}
		
		System.out.println(sb);
	}
}