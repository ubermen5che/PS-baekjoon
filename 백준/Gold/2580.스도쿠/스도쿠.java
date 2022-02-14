import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static HashMap<Integer, Integer> startIdx = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("SDS/firstweek/dayone/prob2580/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[10][10];

        for (int i = 1; i <= 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (i <= 3) {
                startIdx.put(i, 1);
            } else if ( i <= 6) {
                startIdx.put(i, 4);
            } else
                startIdx.put(i, 7);
        }

        sudoku(1, 1);
    }

    static void sudoku(int row, int col) {
        if (col == 10) {
            sudoku(row + 1, 1);
            return;
        }

        // 9x9 board모두 채운 경우라면 정답출력하고 종료
        if (row == 10) {
            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {
                    sb.append(map[i][j] + " ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        // row, col 아직 덜 채워진 경우라면 채운다.
        if (map[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (possibility(row, col, i)) {
                    map[row][col] = i;
                    sudoku(row, col + 1);
                }
            }

            map[row][col] = 0;
            return;
        }

        sudoku(row, col + 1);
    }

    static boolean possibility(int row, int col, int value) {
        // 같은 열에 동일한 숫자가 있는지 확인
        for (int i = 1; i <= 9; i++) {
            if (map[row][i] == value)
                return false;
        }

        // 같은 행에 동일한 숫자 있는지 확인
        for (int i = 1; i <= 9; i++) {
            if (map[i][col] == value)
                return false;
        }

        int startI = startIdx.get(row);
        int startJ = startIdx.get(col);
        // 해당 값이 존재하는 3x3 board내에 같은 값이 있는지 확인
        for (int i = startI; i < startI + 3; i++) {
            for (int j = startJ; j < startJ + 3; j++) {
                if (map[i][j] == value)
                    return false;
            }
        }

        return true;
    }
}