

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long X = Integer.parseInt(st.nextToken());
		long Y = Integer.parseInt(st.nextToken());

		long Z = 100*Y/X;

		if (Z >= 99) {
			System.out.println(-1);
			return;
		}
		long low = Y;
		long high = 2000000000;
		long ans = -1;
		long half;
		while(low<high-1){
			//100*low/high == Z 면 변화 없음, low = (high+low)/2;
			// > Z면 답에 가까워짐 그러나 최소는 확실하지 않음. ans = 100*low/high-Y; high = (high+low)/2;
			half = (low+high)/2;
			long now = 100*half/(X+(half-Y));
			if(now == Z){
				low = half;
			} else{
				ans = half-Y;
				high = half;
			}
		}
		System.out.println(ans);
	}
}
