import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++)
            num[i] = Integer.parseInt(st.nextToken());

        int i = num.length - 1;
        int j = num.length - 1;

        // A[i] > A[i-1] 인 i의 위치를 찾는다. (만약 i의 값이 변화가 없다면 -1 출력)
        // A[j] > A[i-1] 인 j를 찾는다.
        // i-1과 j를 스왑한다.
        // 그 후 num.length - 1부터 i까지의 값을 뒤집는다.

        for (;i > 0; i--) {
            if (num[i] > num[i - 1]) {
                break;
            }
        }

        if (i <= 0) {
            System.out.println(-1);
            System.exit(0);
        }


        for (; i <= j; j--) {
            if (num[j] > num[i-1])
                break;
        }

        swap(i-1, j);
        reverse(i, num.length - 1);

        for (int k = 0; k < num.length; k++) {
            System.out.print(num[k] + " ");
        }
    }

    static public void swap(int a, int b) {
        int tmp = num[a];
        num[a] = num[b];
        num[b] = tmp;
    }

    static public void reverse(int left, int right) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = right; i >= left; i--) {
            list.add(num[i]);
        }

        int k = 0;

        for (int i = left; i <= right; i++) {
            num[i] = list.get(k++);
        }
    }
}