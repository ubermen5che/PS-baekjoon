import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static long[] Dy;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		Dy = new long[N + 1];
	
		Dy[1] = 1;
		
		if (N == 1) {
			System.out.println(1);
			System.exit(0);
		}
		
		Dy[2] = 1;
		
		long answer = 0;
		
		for (int i = 3; i <= N; i++) {
			Dy[i] = Dy[i - 2] + Dy[i - 1];
		}
		
		System.out.println(Dy[N]);
	}

}