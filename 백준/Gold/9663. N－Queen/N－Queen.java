import java.util.Scanner;

public class Main {

    static int N;
    static int answer = 0;
    static int[] board;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        board = new int[15];
        N = sc.nextInt();
        nQueen(0);
        System.out.println(answer);
    }

    public static void nQueen(int depth) {

        if (depth == N ) {
            answer++;
            return;
        }
        for (int i = 0; i < N; i++) {
            board[depth] = i;
            if (promising(depth)) {
                nQueen(depth + 1);
            }
        }
    }

    public static boolean promising(int depth) {

        for (int i = 0; i < depth; i++) {
            if ( board[depth] == board[i] || depth-i == Math.abs(board[depth] - board[i])) {
                return false;
            }
        }
        return true;
    }
}