import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Integer[] sequence;
    static Integer[] operatorInfo = new Integer[4];
    static boolean[] opVisit;
    static Character[] opPerm;
    static ArrayList<Character[]> opPermList;
    static ArrayList<Object[]> expList;
    static Character[] opCharInfo;
    static ArrayList<Integer> resultList;
    static int numOfOp = 0;
    static int max, min;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        sequence = new Integer[N];
        opPermList = new ArrayList<>();
        expList = new ArrayList<>();
        resultList = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(bf.readLine());

        //입력된 수열 배열에 저장
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < 4; i++) {
            operatorInfo[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 4; i++) {
            numOfOp += operatorInfo[i];
        }

        opVisit = new boolean[numOfOp];
        opPerm = new Character[numOfOp];
        //int type operator 형태를 chracter sequence로 변환
        opCharInfo = changeOpInfo(operatorInfo);

        permutation(0); //시간복잡도 P!

        //최대 360만 순회하면서 식 만듬 -> O(N^2) ?
        for (int j = 0; j < opPermList.size(); j++) {
            expList.add(makeExp(opPermList.get(j)));
        }
        //수식 계산함수

        max = -1000000000;
        min = 1000000000;

        //최대 360만 순회하며 계산 -> O(N^2)
        for (int j = 0; j < expList.size(); j++) {
            calculateExp(expList.get(j));
        }

        System.out.println(max);
        System.out.println(min);
    }

    //여기서 operator순열 생성하고 opList에 추가
    //operatorInfo에는 [1,1,1,1]과 같은 정수형태로 op 저장
    static public void permutation(int depth) {

        if (depth == numOfOp) {
            Character[] tmp = new Character[numOfOp];
            for (int i = 0; i < numOfOp; i++) {
                tmp[i] = opPerm[i];
            }
            opPermList.add(tmp);
            return;
        }

        for (int i = 0; i < numOfOp; i++) {
            if (opVisit[i])
                continue;
            opVisit[i] = true;
            opPerm[depth] = opCharInfo[i];
            permutation(depth+1);
            opVisit[i] = false;
        }
    }

    static public Character[] changeOpInfo(Integer[] charInfo) {

        Character[] tmp = new Character[N-1];
        int idx = 0;

        for (int i = 0; i < 4; i++) {
            int j = 0;
            while(charInfo[i] != 0) {
                if (i == 0) { //+ 일경우
                    tmp[idx] = '+';
                    charInfo[i]--;
                } else if (i == 1) {
                    tmp[idx] = '-';
                    charInfo[i]--;
                } else if (i == 2) {
                    tmp[idx] = '*';
                    charInfo[i]--;
                } else if (i == 3) {
                    tmp[idx] = '/';
                    charInfo[i]--;
                }
                idx++;
            }
        }
        return tmp;
    }

    public static void calculateExp(Object[] exp) {
        ArrayList<Object> stack = new ArrayList<>();
        Object element;
        boolean opFlag = false;
        Character op = 0;
        int result = 0;
        Integer tmp = 0;
        int secVal = 0;
        //역순으로 stack에 원소 삽입

        for (int i = exp.length-1; i >= 0; i--) {
            stack.add(exp[i]);
        }

        while(!stack.isEmpty()) {
            element = stack.remove(stack.size()-1);

            //피연산자인 경우
            if (element.getClass() == Integer.class) {
                if (opFlag) {
                    opFlag = false;
                    secVal = (Integer)element;
                    if (op == '+') {
                        result = tmp + secVal;
                        stack.add(result);
                    } else if (op == '-') {
                        result = tmp - secVal;
                        stack.add(result);
                    } else if (op == '*') {
                        result = tmp * secVal;
                        stack.add(result);
                    } else if (op == '/') {
                        result = tmp / secVal;
                        stack.add(result);
                    }
                }
                tmp = (Integer)element;
            } else { //연산자인 경우
                op = (Character)element;
                opFlag = true;
            }
        }
        if (tmp > max)
            max = tmp;
        if (tmp < min)
            min = tmp;
    }

    public static Object[] makeExp(Character[] opCharInfo) {

        ArrayList<Object> exp = new ArrayList<>();
        Object[] tmp = new Object[2];
        Object[] expArr = new Object[2*N-1];

        for (int i = 0; i < (2*N-1) / 2; i++) {
            tmp[0] = sequence[i];
            tmp[1] = opCharInfo[i];
            for (int j = 0; j < 2; j++) {
                exp.add(tmp[j]);
            }
        }

        exp.add(sequence[N-1]);

        for (int i = 0; i < exp.size(); i++) {
            expArr[i] = exp.get(i);
        }
        return expArr;
    }
}
