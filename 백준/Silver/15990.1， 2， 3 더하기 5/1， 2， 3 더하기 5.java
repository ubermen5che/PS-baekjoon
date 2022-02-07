import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int T, N;
	static long[][] Dy;
	static int MOD = 1000000009;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		Dy = new long[100001][4];
		
		Dy[1][1] = 1;
		Dy[2][2] = 1;
		Dy[3][2] = 1;
		Dy[3][1] = 1;
		Dy[3][3] = 1;
		
		for (int j = 4; j <= 100000; j++) {
			for (int k = 1; k <= 3; k++) {
				if (k == 1) {
					Dy[j][k] = ((Dy[j][k] + Dy[j - k][2]) % MOD ) % MOD;
					Dy[j][k] = ((Dy[j][k] + Dy[j - k][3]) % MOD ) % MOD;
				} else if (k == 2) {
					Dy[j][k] = ((Dy[j][k] + Dy[j - k][1]) % MOD ) % MOD;
					Dy[j][k] = ((Dy[j][k] + Dy[j - k][3]) % MOD ) % MOD;
				} else {
					Dy[j][k] = ((Dy[j][k] + Dy[j - k][1]) % MOD ) % MOD;
					Dy[j][k] = ((Dy[j][k] + Dy[j - k][2]) % MOD ) % MOD;
				}
			}
		}
		
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			
			long answer = 0;
			
			for (int j = 1; j <= 3; j++) {
				answer = ( (answer + Dy[N][j]) % MOD ) % MOD;
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}