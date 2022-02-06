/**
 * 
 * @author yong
 * 시간제한 : 1초 메모리 제한 : 256Mb
 * 
 * 시간 복잡도 : N이 최대 1000이므로 길이가 1000인 숫자에 대한 오르막수를 구해야하는데 완전탐색으로는 도저히 불가하다. -> DP
 * 
 *  
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[][] Dy;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		Dy = new int[N + 1][10];
		
		for (int i = 0; i < 10; i++) 
			Dy[1][i] = 1;
		
		int answer = 0;
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= 9; j++) {
				for (int k = 0; k <= j; k++) {
					Dy[i][j] = (Dy[i][j] + Dy[i-1][k]) % 10007;
				}
			}
		}
		for (int j = 0; j <= 9; j++) {
			answer += Dy[N][j];
		}
		
		System.out.println(answer % 10007);
	}

}