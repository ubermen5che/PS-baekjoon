import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[] Dy;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		Dy = new int[100001];
	
		Dy[1] = 3;
		Dy[2] = 7;
		
		for (int i = 3; i <= N; i++) {
			Dy[i] = (Dy[i-2] + 2 * Dy[i-1]) % 9901;
		}
		
		System.out.println(Dy[N]);
	}
}