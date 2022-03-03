import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] cards, queries;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        cards = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++)
            cards[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(cards);

        M = Integer.parseInt(br.readLine());
        queries = new int[M];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++)
            queries[i] = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(binarySearch(queries[i])).append(" ");
        }

        System.out.println(sb);
    }

    static int binarySearch(int target) {
        int left = 0, right = N - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;

            if (cards[mid] > target)
                right = mid - 1;
            else if (cards[mid] < target)
                left = mid + 1;
            else
                break;
        }

        if (cards[mid] == target)
            return 1;
        else
            return 0;
    }
}