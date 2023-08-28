import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  static int N;
  static int[] nums;
  static int value, sum;
  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());

    if (N == 0) {
      System.out.println(0);
      return;
    }

    nums = new int[N];

    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(br.readLine());
    }

    // 절사 진행
    value = Math.round((float) (N * 15) / 100);

    // sort
    Arrays.sort(nums);


    // 절사된 값 제외 평균 계산
    for (int i = value; i < nums.length - value; i++) {
      sum += nums[i];
    }

    System.out.println(Math.round((float) sum / (N - 2 * value)));
  }
}
