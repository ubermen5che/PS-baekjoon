import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시간 제한 : 0.15초 메모리제한 : 128Mb
 *
 * 시간 복잡도 : O(3^N) N이 100000임 따라서 완전탐색 방법으로는 절대 0.15초안에 해결할 수 없는 문제이기 때문에 DP로 접근.
 *
 * 정답의 최대범위 : 최악의 경우 100000에서 계속해서 -1 해서 1이되는 경우는 100000번 연산이 정답이다. 따라서 정답 최대범위는 integer범위 내일것.
 *
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        int Dy[] = new int[1000001];

        Dy[1] = 0;
        Dy[2] = 1;

        //long startTime = System.currentTimeMillis();
        for (int i = 3; i <= N; i++) {
            Dy[i] = Dy[i-1] + 1;
            if (i % 2 == 0)
                Dy[i] = Math.min(Dy[i], Dy[i/2] + 1);
            if (i % 3 == 0)
                Dy[i] = Math.min(Dy[i], Dy[i/3] + 1);
        }
        //long estimatedTime = System.currentTimeMillis() - startTime;
        //System.out.println(estimatedTime / 1000.0);
        System.out.println(Dy[N]);
    }
}