import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간 제한 : 1초 메모리 제한 : 512Mb
 *
 * 문제
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 *
 * 1부터 N까지 자연수 중에서 M개를 고른 수열
 * 같은 수를 여러 번 골라도 된다.
 * 고른 수열은 비내림차순이어야 한다.
 * 길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다.
 * 입력
 * 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
 *
 * 시간 복잡도 : O(M^N) 보다는 작을것이다. 비내림차순이라는 조건때문. 따라서 1초에 1억번 연산이 가능하다고 보고 N,M의 최대가 8일 때를
 * 고려했을 떄 완전탐색으로 충분
 *
 * 공간 복잡도 : O(M)
 */
public class Main {

    static int selected[];
    static int N,M;
    static StringBuilder sb = new StringBuilder();;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[N+1];
        //
        findAnswer(1);
        System.out.println(sb);
    }

    static public void findAnswer(int k) {
        if (k == M+1) {

            for (int cand = 1; cand <= M; cand++)
                sb.append(selected[cand]).append(" ");
            sb.append("\n");
            return;
        }

        for (int cand = 1; cand <= N; cand++) {
            boolean flag = false;

            //이전에 선택된 후보숫자중에 이번에 선택할 후보숫자보다 큰 숫자가 있는지 check (오름차순인지 확인)
            for (int i = k-1; i >= 1; i--) {
                if (selected[i] > cand)
                    flag = true;
            }

            if (!flag) {
                selected[k] = cand;
                findAnswer(k+1);
                selected[k] = 0;
            }
        }
    }
}