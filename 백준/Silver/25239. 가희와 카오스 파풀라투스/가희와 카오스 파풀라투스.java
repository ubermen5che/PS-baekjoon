import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  구현해야할 부분
 *  1. hh:mm형식 -> 영역 변환 함수가 필요.
 *  2.
 */
public class Main {

    static PTime ptime;
    static int[] area = new int[7];
    static boolean[] isLock = new boolean[7];
    static int L, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(),":");
        ptime = new PTime(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 6; i++) {
            area[i] = Integer.parseInt(st.nextToken());
        }

        L = Integer.parseInt(br.readLine());

        for (int i = 0; i < L; i++) {
            String line = br.readLine();
            st = new StringTokenizer(line);
            String firstToken = st.nextToken();
            String secondToken = st.nextToken();

            StringTokenizer timeTokenizer = new StringTokenizer(firstToken, ".");
            int s = Integer.parseInt(timeTokenizer.nextToken());
            int T = Integer.parseInt(timeTokenizer.nextToken());

            //1분이 지났으면 패턴 종료
            if (s >= 60) {
                break;
            }

            // 포탈에서 키보드 위쪽 키 누른 경우 -> 현재 파풀라투스 시계에 영역을 봉인한다.
            if (secondToken.equals("^")) {
                int area = getArea();
                isLock[area] = true;
            }
            // NMIN, NHOUR형태의 문자열 -> PTime 객체에 담긴 시간을 변경해준다.
            else {
                StringTokenizer tTokenizer = new StringTokenizer(secondToken, "MIN|HOUR");
                int time = Integer.parseInt(tTokenizer.nextToken());

                if (secondToken.contains("MIN")) {
                    ptime.m += time;
                    if (ptime.m >= 60) {
                        ptime.h++;
                        if (ptime.h > 12) {
                            ptime.h = ptime.h % 12;
                        }
                        ptime.m = ptime.m % 60;
                    }
                } else {
                    ptime.h += time;
                    if (ptime.h > 12) {
                        ptime.h = ptime.h % 12;
                    }
                }
            }
        }

        for (int i = 1; i <= 6; i++) {
            if (!isLock[i]) {
                answer += area[i];
            }
        }

        if (answer >= 100) {
            System.out.println(100);
        } else {
            System.out.println(answer);
        }
    }

    static int getArea() {
        // 1번영역 12 <-> 2
        // 12:01 ~ 1:59
        if ((ptime.h == 12 || ptime.h == 1) && ptime.m >= 1 && ptime.m <= 59) {
            return 1;
        }
        // 2번영역 2:01 ~ 3:59
        else if ((ptime.h == 2 || ptime.h == 3) && ptime.m >= 1 && ptime.m <= 59) {
            return 2;
        }
        // 3번영역 4:01 ~ 5:59
        else if ((ptime.h == 4 || ptime.h == 5) && ptime.m >= 1 && ptime.m <= 59) {
            return 3;
        }
        // 4번영역 6:01 ~ 7:59
        else if ((ptime.h == 6 || ptime.h == 7) && ptime.m >= 1 && ptime.m <= 59) {
            return 4;
        }
        // 5번영역 8:01 ~ 9:59
        else if ((ptime.h == 8 || ptime.h == 9) && ptime.m >= 1 && ptime.m <= 59) {
            return 5;
        }
        // 6번 영역 10:01 ~ 11:59
        else if ((ptime.h == 10 || ptime.h == 11) && ptime.m >= 1 && ptime.m <= 59) {
            return 6;
        } else {
            return 0;
        }
    }
    static class PTime {
        int h, m;

        public PTime(int h, int m) {
            this.h = h;
            this.m = m;
        }
    }
}
