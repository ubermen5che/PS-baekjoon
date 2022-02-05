import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] cost;
	static int[][] Dy;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		cost = new int[N + 1][3];
		Dy = new int[N + 1][3];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				Dy[i][0] = Math.min(Dy[i-1][1] + cost[i][0], Dy[i-1][2] + cost[i][0]);
				Dy[i][1] = Math.min(Dy[i-1][0] + cost[i][1], Dy[i-1][2] + cost[i][1]);
				Dy[i][2] = Math.min(Dy[i-1][0] + cost[i][2], Dy[i-1][1] + cost[i][2]);
			}
		}
		
		int answer = Integer.MAX_VALUE;
		
		for (int i = 0; i < 3; i++) {
			answer = Math.min(answer, Dy[N][i]);
		}
		
		System.out.println(answer);
	}

}