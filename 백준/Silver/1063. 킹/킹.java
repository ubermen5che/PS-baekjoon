import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] board = new int[9][9];
    static int[] dy = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dx = new int[] {1, 1, 0, -1, -1, -1, 0, 1}; //R, RB, B, LB, L, LT, T, LT
    static XY kingXY, rockXY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String kingPos = st.nextToken();
        String rockPos = st.nextToken();
        int N = Integer.parseInt(st.nextToken());

        kingXY = getXY(kingPos.charAt(0), kingPos.charAt(1));
        rockXY = getXY(rockPos.charAt(0), rockPos.charAt(1));

        board[kingXY.y][kingXY.x] = 1;
        board[rockXY.y][rockXY.x] = 2;

        for (int i = 0; i < N; i++) {
            String strDir = br.readLine();
            int dir = getDir(strDir);
            int kingNY = kingXY.y + dy[dir];
            int kingNX = kingXY.x + dx[dir];

            // 킹이 보드를 벗어나는 경우
            if (kingNY <= 0 || kingNY > 8 || kingNX <= 0 || kingNX > 8) continue;
            // 킹이 이동한 칸에 돌이 존재하는 경우
            if (board[kingNY][kingNX] == 2) {
                int rockNY = kingNY + dy[dir];
                int rockNX = kingNX + dx[dir];

                // 돌을 이동시켰더니 board 범위 밖인 경우 continue
                if (rockNY <= 0 || rockNY > 8 || rockNX <= 0 || rockNX > 8) continue;
                // 돌을 다음 칸으로 이동시켜준다.
                board[rockXY.y][rockXY.x] = 0;
                board[rockNY][rockNX] = 2;
                rockXY.y = rockNY;
                rockXY.x = rockNX;
            }

            // 킹을 이동 시킨다
            board[kingXY.y][kingXY.x] = 0;
            board[kingNY][kingNX] = 1;
            kingXY.y = kingNY;
            kingXY.x = kingNX;
        }

        printAnswer();
    }

    static void printAnswer() {
        String answer = "";

        if (kingXY.x == 1) {
            answer += "A";
        } else if (kingXY.x == 2) {
            answer += "B";
        } else if (kingXY.x == 3) {
            answer += "C";
        } else if (kingXY.x == 4) {
            answer += "D";
        } else if (kingXY.x == 5) {
            answer += "E";
        } else if (kingXY.x == 6) {
            answer += "F";
        } else if (kingXY.x == 7) {
            answer += "G";
        } else if (kingXY.x == 8) {
            answer += "H";
        }

        answer += 9 - kingXY.y;

        answer += "\n";

        if (rockXY.x == 1) {
            answer += "A";
        } else if (rockXY.x == 2) {
            answer += "B";
        } else if (rockXY.x == 3) {
            answer += "C";
        } else if (rockXY.x == 4) {
            answer += "D";
        } else if (rockXY.x == 5) {
            answer += "E";
        } else if (rockXY.x == 6) {
            answer += "F";
        } else if (rockXY.x == 7) {
            answer += "G";
        } else if (rockXY.x == 8) {
            answer += "H";
        }

        answer += 9 - rockXY.y;
        answer += "\n";

        System.out.println(answer);
    }

    static int getDir(String strDir) {
        if (strDir.equals("R")) return 0;
        if (strDir.equals("RB")) return 1;
        if (strDir.equals("B")) return 2;
        if (strDir.equals("LB")) return 3;
        if (strDir.equals("L")) return 4;
        if (strDir.equals("LT")) return 5;
        if (strDir.equals("T")) return 6;
        if (strDir.equals("RT")) return 7;

        return 0;
    }

    static XY getXY(Character X, Character Y) {
        XY pos = new XY();

        if (X == 'A') {
            pos.x = 1;
        } else if (X == 'B') {
            pos.x = 2;
        } else if (X == 'C') {
            pos.x = 3;
        } else if (X == 'D') {
            pos.x = 4;
        } else if (X == 'E') {
            pos.x = 5;
        } else if (X == 'F') {
            pos.x = 6;
        } else if (X == 'G') {
            pos.x = 7;
        } else if (X == 'H') {
            pos.x = 8;
        }

        int row = Y - '0';
        row = 9 - row;
        pos.y = row;

        return pos;
    }

    static class XY {
        int y, x;
        public XY (int y, int x) {
            this.y = y;
            this.x = x;
        }

        public XY() {

        }
    }
}
