import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, S, M;
	static int[] V;
	static boolean[][] Dy;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		V = new int[N + 1];
		Dy = new boolean[N + 2][M + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		Dy[1][S] = true;
		
		for (int now = 1; now <= N; now++) {
			boolean flag = false;
			for (int vol = 0; vol <= M; vol++) {
				if (!Dy[now][vol]) continue;
				
				for (int volume : new int[] {vol + V[now], vol - V[now]}) {
					if (volume < 0 || volume > M) continue;
					Dy[now + 1][volume] = true;
					flag = true;
				}
			}
			
			if (!flag) {
				System.out.println(-1);
				System.exit(0);
			}
		}
		
		int answer = 0;
		
		for (int i = 0; i <= M; i++) {
			if (Dy[N + 1][i]) {
				answer = Math.max(answer, i);
			}
		}
		
		System.out.println(answer);
	}

}
