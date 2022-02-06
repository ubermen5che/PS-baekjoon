import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static long[][] Dy;
	static int N;
	static int[] A;
	static int sum = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		A = new int[N + 1];
		Dy = new long[N + 1][21];
		
		long answer = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Dy[2][A[1]] = 1;
		answer = findAnswer(A[N], N);
		sb.append(answer).append("\n");
		
		System.out.println(sb);
	}
	
	static long findAnswer(int value, int depth) {
		if (Dy[depth][value] != 0 || depth <= 2) {
			return Dy[depth][value];
		} else {
			if (value - A[depth - 1] < 0 && value + A[depth - 1] > 20)
				return 0;
			
			if (value - A[depth - 1] >= 0)
				Dy[depth][value] += findAnswer(value - A[depth - 1], depth - 1);
			if (value + A[depth - 1] <= 20)
				Dy[depth][value] += findAnswer(value + A[depth - 1], depth - 1);
		}
		
		return Dy[depth][value];
	}
}