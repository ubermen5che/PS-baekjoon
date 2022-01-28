import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] sequence, indexOrder;
    static int[] LIS;
    static int[] D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        sequence = new int[N + 1];
        indexOrder = new int[N + 1];
        LIS = new int[N + 1];
        D = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        D[1] = sequence[1];
        indexOrder[1] = 1;
        int DLength = 1;
        //이분탐색 하며 D배열 채워줌
        for (int i = 2; i <= N; i++) {
            int nextIdx = binarySearch(sequence[i], 1, DLength);
            indexOrder[i] = nextIdx;
            if (nextIdx > DLength) { //
                DLength++;
                D[nextIdx] = sequence[i];
            } else {
                D[nextIdx] = sequence[i];
            }
        }

        int answer = DLength;

        //찾은 Indexorder로 LIS채워줌
        for (int i = N; i >= 1; i--) {
            if (DLength == indexOrder[i]) {
                LIS[DLength] = sequence[i];
                DLength--;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer).append("\n");
        for (int i = 1; i <= answer; i++)
            sb.append(LIS[i]).append(" ");

        System.out.print(sb);
    }

    static int binarySearch(int target, int left, int right) {

        while (left <= right) {
            int mid = (left + right) / 2;

            if (D[mid] > target)
                right = mid - 1;
            else if (D[mid] < target) {
                left = mid + 1;
            } else
                return mid;
        }
        return left;
    }
}
