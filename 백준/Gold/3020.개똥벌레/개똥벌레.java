import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.*;

public class Main {

	static int[] bottom, top;
	static int[] map;
	static int N, H, cnt = 0;
	static int gMin = Integer.MAX_VALUE;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		bottom = new int[N / 2];
		top = new int[N / 2];
		map = new int[N];
		
		stubArray();
		int b = 0;
		int t = 0;
		
		ArrayList<Integer> hList = new ArrayList<Integer>();
		
		for (int h = 1; h <= H; h++) {
			b = binarySearch(0, N / 2 - 1, h, bottom);
			t = binarySearch(0, N / 2 - 1, H - h + 1, top);
			
			gMin = Math.min(gMin, b + t);
			hList.add(b + t);
			
		}
		
		for (Integer i : hList) {
			if (gMin == i)
				cnt++;
		}
		
		System.out.println(gMin + " " + cnt);
		
	}

	static int binarySearch(int left, int right, int height, int[] arr) {
		int mid = 0;
		int min = Integer.MAX_VALUE;
		while (left <= right) {
			mid = (left + right) / 2;
			
			//현재 arr에서 중간위치에 저장된 높이값이 height보다 큰 경우
			if (arr[mid] >= height) {
				right = mid - 1;
				// 같은 높이의 석주, 종유석이 존재할 때 그 중에서 가장 왼쪽에 있는 index저
				min = Math.min(min, mid);
			} else
				left = mid + 1;
		}
		
		return min == Integer.MAX_VALUE ? 0 : (N / 2) - min;
	}
	
	static void stubArray() throws IOException{
		int b = 0;
		int t = 0;
		
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				bottom[b++] = Integer.parseInt(br.readLine());
			}
			else {
				top[t++] = Integer.parseInt(br.readLine());
			}
		}
		
		Arrays.sort(bottom);
		Arrays.sort(top);
		
	}
}