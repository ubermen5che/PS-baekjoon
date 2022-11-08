import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 어떤 자료구조를 사용?
 *
 * 정렬해서 조건에 따라 처리해주면 풀릴듯.
 *
 */
public class Main {
    static int N;
    static Integer[] heights;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        heights = new Integer[N];

        int sum = 0;
        int sumOfQ = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
            sum += heights[i];
            sumOfQ += heights[i] / 2;
        }

        // 3의 배수라면 YES 가능성 존재
        if (sum % 3 == 0) {
            if (sum / 3 <= sumOfQ) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } else {
            System.out.println("NO");
        }
    } // End of main
}
