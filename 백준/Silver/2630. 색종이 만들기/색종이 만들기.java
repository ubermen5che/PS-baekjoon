import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int white = 0, blue = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findAnswer(1, 1, N);
        System.out.println(white);
        System.out.println(blue);
    }

    static void findAnswer(int row, int col, int size) {
        if (check(row, col, size))
            return;
        else {
            int newSize = size / 2;
            // 1사분면
            findAnswer(row, col, newSize);
            // 2사분면
            findAnswer(row, col + newSize, newSize);
            // 3사분면
            findAnswer(row + newSize, col, newSize);
            // 4사분면
            findAnswer(row + newSize, col + newSize, newSize);
        }
    }

    static boolean check(int row, int col, int size) {
        int color = map[row][col];
        boolean flag = true;

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (color != map[i][j]) {
                    return false;
                }
            }
        }

        if (flag && color == 0) {
            white++;
            return true;
        } else if (flag && color == 1) {
            blue++;
            return true;
        }
        return true;
    }
}