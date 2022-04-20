import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] lights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        lights = new int[N];

        st = new StringTokenizer(br.readLine());

        int i = 0;

        while(st.hasMoreTokens()) {
            lights[i++] = Integer.parseInt(st.nextToken());
        }

        int a, b, c;

        /**
         *  cmd format [a, b, c]
         *  a == 명령어 타입 (1, 2, 3, 4번 명령 존재)
         *
         *  1번 명령어일 경우 (1, i, x ) i번째 전구를 x로 변경
         *  2번 명령 : (2, l, r) l부터 r까지의 전구를 flip
         *  3번 명령 : (3, l, r) l부터 r까지 전구 끔
         *  4번 명령 : (4, l, r) l부터 r까지 전구 킴
         */
        for (i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                lights[b - 1] = c;
            } else if (a == 2) {
                for (int l = b - 1; l < c; l++) {
                    if (lights[l] == 0)
                        lights[l] = 1;
                    else if (lights[l] == 1)
                        lights[l] = 0;
                }
            } else if (a == 3) {
                for (int l = b - 1; l < c; l++) {
                    lights[l] = 0;
                }
            } else {
                for (int l = b - 1; l < c; l++) {
                    lights[l] = 1;
                }
            }
        }

        for (int j = 0; j < lights.length; j++) {
            System.out.print(lights[j] + " ");
        }
    }
}
