/**
 * 
 * @author yong
 * 시간제한 : 2초 메모리 제한 : 128Mb
 * 
 * 시간 복잡도 : 완전 탐색시 O(N^N) 정도의 어마어마한 경우의 수 존재 -> DP로 해결
 * 
 *  
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[] A;
	static int[] Dy;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		A = new int[N + 1];
		Dy = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		Dy[1] = A[1];
		
		if (N >= 2) {
			Dy[2] = Dy[1] + A[2];
			if (N == 2) {
				System.out.println(Dy[N]);
				System.exit(0);
			}
		}
		
		// case 1 : oox
		// case 2 : oxo
		// case 3 : xoo
		// 점화식 : Dy[i] = Math.max(Dy[i-1], Math.max(Dy[i-2] + A[i], Dy[i-3] + Dy[i-1] + A[i]));
		
		for (int i = 3; i <= N; i++) {
			Dy[i] = Math.max(Dy[i-1], Math.max(Dy[i-2] + A[i], Dy[i-3] + A[i-1] + A[i]));
		}
		
		System.out.println(Dy[N]);
	}

}