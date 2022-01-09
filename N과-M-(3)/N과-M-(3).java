import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int ansArr[];
    static int N,M;
    static StringBuilder sb = new StringBuilder();;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ansArr = new int[N+1];
        //
        findAnswer(1);
        System.out.println(sb);
    }

    //만약 M개의 숫자 전부 고름 -> 조건에 맞는 탐색을 한가지 성공한 것.
    //아직 M개를 고르지 못함 -> k번째 부터 M번째 원소를 조건에 맞게 고르는 방법 모두를 시도한다.
    static public void findAnswer(int k) {
        if (k == M+1) {   //다 골랐으면
            for (int i = 1; i <= M; i++) {
                sb.append(ansArr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int cand = 1; cand <= N; cand++) {
            ansArr[k] = cand;
            findAnswer(k+1);
            ansArr[k] = 0;
        }
    }
}