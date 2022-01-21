import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] nums, LR, RL;
    static int N;
    static int max;
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("SDS/firstweek/dayfour/prob14476/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        LR = new int[N];
        RL = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        //LR 만들기 (누적 최대공약수를 만듬)
        LR[0] = nums[0];
        for (int i = 1; i < N; i++) {
            LR[i] = gcd(LR[i - 1], nums[i]);
        }

        //RL 만들기
        RL[N-1] = nums[N-1];
        for (int i = N - 2; i >= 0; i--) {
            RL[i] = gcd(RL[i + 1], nums[i]);
        }

        int maxPos = 0;
        max = -1;
        //nums에서 특정수를 제외하고나서의 최대 공약수를 구함
        for (int k = 0; k < N; k++) {
            int gcd = 0;

            //왼쪽 끝일 경우
            if (k == 0) {
                gcd = RL[1];
            }
            //오른쪽 끝일 경우
            else if(k == N - 1) {
                gcd = LR[N - 2];
            }
            //왼쪽, 오른쪽 구간으로 나뉘는 경우
            //
            else {
                gcd = gcd(LR[k-1], RL[k+1]);
            }

            if (nums[k] % gcd != 0 && max < gcd) {
                max = gcd;
                maxPos = k;
            }
        }

        if (max != -1)
            System.out.println(max + " " + nums[maxPos]);
        else
            System.out.println(-1);
    }
    //
    static int gcd(int a, int b) {
        int r;

        while (true) {
            r = a % b;
            if (r == 0)
                return b;
            a = b;
            b = r;
        }
    }
}