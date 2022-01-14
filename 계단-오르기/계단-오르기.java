import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  시간 제한 : 1초
 *
 *  시간 복잡도 : 완전 탐색시 2^300 이라는 어마어마한 경우의 수가 존재. 즉, 완전 탐색으로는 해결이 불가능함으로 DP사용을 고려
 *
 *  정답 최대 범위 : 계단이 300개이고, 각 계단의 최대점수는 10000이하 이므로 최악의 경우 300만임. 따라서 integer범위내이다.
 *  
 *  문제 해법 : 이전에 방문한 계단 정보를 Dynamic table에 표현해서 점화식을 세움으로써 문제를 해결.
 *  Dy[i][0]에는 i-1계단을 통하지않고 i번째 계단을 오른 경우의 최대점수, Dy[i][1]에는 i-1계단을 통해서 i번째 계단에 오른 최대점수
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int A[] = new int[N+1];
        int Dy[][] = new int[N+1][2]; //계단의 이전 방문 정보도 포함해서 Dynamic table을 만듬.

        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(bf.readLine());
        }

        Dy[0][0] = 0;
        Dy[0][1] = 0;
        Dy[1][0] = A[1];
        Dy[1][1] = 0;

        for (int i = 2; i <= N; i++) {
            Dy[i][0] = Math.max(Dy[i-2][0] + A[i], Dy[i-2][1] + A[i]);
            Dy[i][1] = Dy[i-1][0] + A[i];
        }
        System.out.println(Math.max(Dy[N][0], Dy[N][1]));
    }
}