import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int T,N,M;
    static Long[] A, B;
    static ArrayList<Long> subA, subB;
    static Long answer = 0L;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("SDS/firstweek/daytwo/prob2143/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(bf.readLine());
        N = Integer.parseInt(bf.readLine());

        subA = new ArrayList<>();
        subB = new ArrayList<>();

        A = new Long[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++)
            A[i] = Long.parseLong(st.nextToken());

        M = Integer.parseInt(bf.readLine());

        st = new StringTokenizer(bf.readLine());

        B = new Long[M];

        for (int i = 0; i < M; i++)
            B[i] = Long.parseLong(st.nextToken());

        makeSubArr(A, subA);
        Collections.sort(subA);
        makeSubArr(B, subB);
        Collections.sort(subB, Comparator.reverseOrder());

        long result = 0;
        int ptA = 0;
        int ptB = 0;

        while (ptA < subA.size() && ptB < subB.size()) {
            long currentA = subA.get(ptA);
            long target = T - currentA;
            //currentB == target -> 동률체크 (subA, subB 개수 체크 -> 답구하기 ptA+, ptB+)
            if (subB.get(ptB) == target) {
                long countA = 0;
                long countB = 0;
                while (ptA < subA.size() && subA.get(ptA) == currentA) {
                    countA++;
                    ptA++;
                }
                while (ptB < subB.size() && subB.get(ptB) == target) {
                    countB++;
                    ptB++;
                }
                answer += countA * countB;
            }
            //currentB > target -> ptB++
            else if (subB.get(ptB) > target) {
                ptB++;
            }
            //currentB < target -> ptA++
            else {
                ptA++;
            }
        }
        /**
        //two pointer
        int p1 = 0;
        int p2 = subB.size()-1;
        Long sum = 0L;

        while (p1 < subA.size() && p2 >= 0) {
            sum = subA.get(p1) + subB.get(p2);
            //sum < T
            if (sum < T) {
                p1++;
            } else if (sum > T) {
                p2--;
            } else { //같은 경우 인접한 곳에서 동일한 값 있는지 체크
                int cntA = 1 ;
                int cntB = 1;
                int cP1 = p1;
                int cP2 = p2;

                //subA체크
                while (true) {
                    if (cP1 + 1 < subA.size()) {
                        subA.get(++cP1) == subA.get(p1)
                    }
                    cntA++;
                }
                //subB에 중복 체크
                while (subB.get(--cP2) == subB.get(p2)) {
                    if (cP2 < 0)
                        break;
                    cntB++;
                }

                if (cntA == 1 && cntB == 1) {
                    answer++;
                    p2--;
                    continue;
                }

                answer += cntA * cntB;

                p1 += cntA;
                if (cntB > 1)
                    p2 -= cntB;
            }
        }
         **/
        System.out.println(answer);
    }

    public static void makeSubArr(Long[] arr, ArrayList<Long> subArr) {
        Long Dy = 0L;

        for (int i = 0; i < arr.length; i++) {
            Dy = arr[i];
            subArr.add(Dy);
            for (int j = i+1; j < arr.length; j++) {
                Dy += arr[j];
                subArr.add(Dy);
            }
        }
    }
}
