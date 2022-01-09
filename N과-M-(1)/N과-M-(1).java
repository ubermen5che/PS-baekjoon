import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간제한 : 1초 메모리 제한 : 512Mb
 * 문제
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 *
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 *
 * 입력
 * 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
 *
 *
 * 시간 복잡도 : O(M!) 최대 탐색 경우의 수 8! = 40320
 *
 */
public class Main {

    static int N,M;
    static StringBuilder sb = new StringBuilder();
    static int ansArr[];
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ansArr = new int[N+1];
        visited = new boolean[N+1];

        searchAns(1);
        System.out.println(sb);
    }

    //중복이 없이 N개의 수중에서 M의 길이를 가지는 수열을 출력해야한다. (방문 표시할 배열 이용)
    //M개 모두 뽑은 경우 -> 조건에 만족하는 수열 한가지를 얻는다.
    //M개를 뽑지 못한 경우 -> k번째 부터 M번째 원소를 조건에 맞게 고르는 방법 모두를 시도한다.
    static public void searchAns(int k) {
        if (k == M+1) {
            for (int cand = 1; cand <= M; cand++)
                sb.append(ansArr[cand]).append(" ");
            sb.append("\n");

            return;
        }

        for (int cand = 1; cand <= N; cand++) {
            if (!visited[cand]) {
                visited[cand] = true;
                ansArr[k] = cand;
                searchAns(k + 1);
                ansArr[k] = 0;
                visited[cand] = false;
            }
        }
    }
}