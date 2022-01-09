import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N, M;

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int rectangle[][] = new int[N+1][M+1];

        String line;

        for (int i = 1; i <= N; i++) {
           line = bf.readLine();
           for (int j = 1; j <= line.length(); j++) {
               rectangle[i][j] = Integer.parseInt(String.valueOf(line.charAt(j-1)));
           }
        }

        int min = Math.min(N, M);
        int iteration;
        int pivot[] = new int[2];
        int size = min;

        pivot[0] = 1;
        pivot[1] = 1;

        while(true) {
            //iteration은 size가 작아질때 마다 갱신이 되어야한다.
            //size는 점차 작아진다.
            iteration = (N - size+1) * (M - size+1);

            for (int i = 0; i < iteration; i++) {
                //꼭짓점 4위치가 모두 같으면 최대
                if ((rectangle[pivot[0]][pivot[1]] == rectangle[pivot[0]+size-1][pivot[1]]) &&
                        (rectangle[pivot[0]][pivot[1]] == rectangle[pivot[0]+size-1][pivot[1]+size-1]) &&
                        (rectangle[pivot[0]][pivot[1]] == rectangle[pivot[0]][pivot[1]+size-1])){
                    System.out.println(size * size);
                    System.exit(0);
                } else {
                    //pivot어떻게 업데이트 시키지?
                    //우선 column에 대한 pivot을 증가시키며 더이상 pivot이동 불가할 때 rowpivot증가
                    //row pivot마저 더이상 증가시킬 수 없다면 break;

                    if (pivot[1] < M-size+1)
                        pivot[1]++;
                    else {
                        if (pivot[0] < N-size+1) {
                            pivot[0]++;
                            pivot[1] = 1;
                        } else {
                            size--;
                            pivot[0] = 1;
                            pivot[1] = 1;
                        }
                    }
                }
            }
        }
    }
}