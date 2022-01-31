import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] Dy;
    static String N;
    static int K, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = st.nextToken();
        K = Integer.parseInt(st.nextToken());
        M = N.length();

        Dy = new int[K + 1][1000001];
        System.out.println(dfs(N, 0));
    }

    static int dfs(String N, int depth) {
        int num = Integer.parseInt(N);
        if (depth == K) {
            return num;
        }

        //같은 depth에서 메모이제이션 되어있다면 저장된 값을 리턴
        if (Dy[depth][num] != 0)
            return Dy[depth][num];

        int rtn = -1;

        //i번째 수와 j번째수를 교환 하는 로직
        for (int i = 0; i < M; i++) {
            for (int j = i + 1; j < M; j++) {
                if (i == 0 && N.charAt(j) == '0') continue;
                String changedN = swap(N, i, j);
                rtn = Math.max(rtn, dfs(changedN, depth + 1));
            }
        }

        Dy[depth][num] = rtn;

        return rtn;
    }

    static String swap(String numOfStr, int i, int j) {
        char[] tmp = numOfStr.toCharArray();
        char chTmp = tmp[i];

        tmp[i] = tmp[j];
        tmp[j] = chTmp;

        return String.valueOf(tmp);
    }
}