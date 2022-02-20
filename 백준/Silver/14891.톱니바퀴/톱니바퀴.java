import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int[][] gear;
    static int K, gearNum, dir, answer;
    static int[] score = new int[] {0, 1, 2, 4, 8};
    static HashMap<Integer, int[]> adjPos = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        gear = new int[4 + 1][8 + 1];
        for (int i = 1; i <= 4; i++) {
            String input = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j + 1] = input.charAt(j) - '0';
            }
        }

        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            gearNum = Integer.parseInt(st.nextToken());
            dir = Integer.parseInt(st.nextToken());
            findAnswer(gearNum, dir, 0);
        }

        for (int i = 1; i <= 4; i++) {
            if (gear[i][1] == 1) {
                answer += score[i];
            }
        }

        System.out.println(answer);
    }

    static void findAnswer(int num, int direction, int prev) {
        // 범위를 벗어난 경우 return
        if (num > 4 || num <= 0)
            return;

        // 인접한 기어들의 상태를 먼저 확인 한다
        // case 1: 맞닿은 기어들 중 극이 모두 같다면 return한다.
        if (num - 1 >= 1) {
            // 현재 위치에서 왼쪽에 gear가 위치해있다는 뜻 -> 맞닿은 기어 극 확인 -> 왼쪽톱니는 항상 현재 기준 왼쪽 기어의 3번 위치이다.
            // case 1 : 현재기어 7번과 왼쪽 기어 3번의 극이 다른 경우 왼쪽기어로 이동해서 회전 가능성 확인.
            if (num != prev + 1 && gear[num][7] != gear[num - 1][3]) {
                findAnswer(num - 1, direction * -1, num);
            }
        }

        if (num + 1 <= 4 ) {
            // 현재 위치에서 오른쪽에 gear가 위치해있다는 뜻 -> 맞닿은 기어 극 확인 -> 오른쪽 톱니의 인접부분은 항상 현재 기준 오른쪽 기어의 7번 위치이다.
            // 오른쪽 가능성 검사시 이전에 오른쪽 톱니바퀴에서 영향을 받아 움직일 경우 오른쪽 톱니바퀴 경우 제외
            // case 1 : 현재기어 3번과 오른쪽 기어 7번의 극이 다른 경우 오른쪽기어로 이동해서 회전 가능성 봄
            if (num != prev - 1 && gear[num][3] != gear[num + 1][7]) {
                findAnswer(num + 1, direction * -1, num);
            }
        }

        rotateGear(direction, num);

        return;
    }

    static void rotateGear(int dir, int num) {
        // 시계방향 회전
        if (dir == 1) {
            int tmp = gear[num][8];
            for (int i = 8; i >= 2; i--) {
                gear[num][i] = gear[num][i-1];
            }
            gear[num][1] = tmp;
        } else {
            int tmp = gear[num][1];
            for (int i = 1; i <= 7; i++) {
                gear[num][i] = gear[num][i+1];
            }
            gear[num][8] = tmp;
        }
    }
}
