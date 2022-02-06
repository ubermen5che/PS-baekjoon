/**
 * 
 * @author yong
 * 시간제한 : 1초 메모리 제한 : 256Mb
 * 
 * 시간 복잡도 : 완전탐색을 할 경우 자릿수의 최대는 64이므로 10^64크기의 어떤 수에 대해 오르막수를 구해야한다. -> 경우의수가
 * 너무 많으므로 DP로 접근한다.
 * 
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static long[][] Dy;
	static int N, T;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			Dy = new long[N + 1][10];
			
			for (int i = 0; i < 10; i++) 
				Dy[1][i] = 1;
			
			long answer = 0;
			
			for (int i = 2; i <= N; i++) {
				for (int j = 0; j <= 9; j++) {
					for (int k = 0; k <= j; k++) {
						Dy[i][j] = (Dy[i][j] + Dy[i-1][k]);
					}
				}
			}
			for (int j = 0; j <= 9; j++) {
				answer += Dy[N][j];
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
    }
}