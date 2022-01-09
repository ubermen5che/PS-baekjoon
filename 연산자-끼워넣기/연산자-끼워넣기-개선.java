import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간 제한 2초
 * 메모리 제한 512Mb
 *
 * 시간 복잡도 : O(nPm)
 * 이유 : 문제에서 N개의 수로 이루어진 수열이 주어진다. 이 수열은 고정이고 연산자를 수열 사이사이에 끼워넣어야함.
 * 이 때 연산자는 한번만 사용해야하고 순서가 있으므로 시간복잡도는 O(nPm)이다. 이 문제의 경우 연산자의 갯수는 N-1이므로
 * 10! = 3628800번의 연산을 통해 조건에 해당하는 연산자의 경우의 수를 구할 수 있다.
 *
 * 공간 복잡도 : 연산 순서 저장을 위한 공간 O(N+1)
 *
 * 최대, 최소 출력범위
 * int
 *
 *
 */
public class Main {

    static int N;
    static int max, min;
    static int[] nums, operators, order;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        nums = new int[N+1];
        order = new int[N+1];
        operators = new int[5];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= 4; i++)
            operators[i] = Integer.parseInt(st.nextToken());

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        recFunc(1);
        System.out.println(max);
        System.out.println(min);
    }

    static public int calculate() {
        int value = nums[1];

        //이미 만들어진 연산자 순서와 입력된 numbers배열을 순회하며 연산 진행
        for (int i = 1; i < N; i++) {
            if (order[i] == 1) {            // +
                value = value + nums[i+1];
            } else if (order[i] == 2) {     // -
                value = value - nums[i+1];
            } else if (order[i] == 3) {     // *
                value = value * nums[i+1];
            } else if (order[i] == 4) {
                value = value / nums[i+1]; // /
            }
        }
        return value;
    }

    static public void recFunc(int k) {
        if (k == N) {  //N-1개의 연산자의 순서가 모두 결정되었을 때 종료
            //정해진 연산자 순서에 따라 식을 계산하고 최대, 최소 갱신
            int value = calculate();

            min = Math.min(min, value);
            max = Math.max(max, value);

        } else { // N-1개의 연산 순서가 아직 정해지지 않았음. 정하기 위해 탐색
            //여기서 후보군들은 연산자임.
            for (int cand = 1; cand <= 4; cand++) {
                //아직 사용되지 않은 연산자가 존재해야 order결정할 수 있음.
                if (operators[cand] > 0) {
                    order[k] = cand;
                    operators[cand]--;
                    recFunc(k+1);
                    operators[cand]++;
                    order[k] = 0;
                }
            }
        }
    }
}