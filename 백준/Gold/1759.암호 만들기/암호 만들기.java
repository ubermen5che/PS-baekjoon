import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int L,C;
    static char[] charArr;
    static char[] password;

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        charArr = new char[C];
        password = new char[L];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < C; i++)
            charArr[i] = st.nextToken().charAt(0);

        Arrays.sort(charArr);
        for (int i = 0; i < C; i++) {
            password[0] = charArr[i];
            if (isMoum(charArr[i])) dfs(1, i+1,1, 0);
            else dfs(1, i+1,0, 1);
        }

        System.out.println(sb);
    }

    static boolean isMoum(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') return true;
        else return false;
    }

    static void dfs(int k, int start, int numOfMo, int numOfJa) {
        //체크인 -> 문자 오름차순 정렬로 인해 생략할 수 있다.
        //목적지
        if (k >= L ) {
            if (numOfMo >= 1 && numOfJa >= 2)
                sb.append(String.valueOf(password)).append("\n");
            return;
        }

        //노드 순회
        for (int i = start; i < C; i++) {
            password[k] = charArr[i];
            if (isMoum(charArr[i])) {
                dfs(k+1, i+1, numOfMo + 1, numOfJa);
            }
            else {
                dfs(k+1, i+1, numOfMo, numOfJa + 1);
            }
        }
    }
}