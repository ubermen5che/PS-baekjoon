import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static char[] charList = new char[]
            {'b', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'o',
             'p', 'q', 'r', 's', 'u', 'v', 'w', 'x', 'y', 'z'};
    static ArrayList<Character> comb = new ArrayList<>();
    static String[] words;
    static int ans;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        if (K < 5) System.out.println(ans);
        else {
            findAnswer(K - 5, 0, 0);
            System.out.println(ans);
        }
    }
    
    static void findAnswer(int r, int cnt, int next) {
        if (r == cnt) {
            //System.out.println(comb);
            int wordCnt = 0;
            // 현재 뽑은 문자들로 얼마나 많은 문자를 읽을 수 있는지 확인
            // a, c, i, t, n는 이미 조합에 포함되어있다고 가정
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                boolean isRead = true;
                for (int j = 0; j < word.length(); j++) {
                    char ch = word.charAt(j);
                    if (ch == 'a' || ch == 'c' || ch == 'i' || ch == 't' || ch == 'n') continue;
                    if (!comb.contains(ch)) {
                        isRead = false;
                        break;
                    }
                }

                if (isRead) wordCnt++;
            }

            ans = Math.max(ans, wordCnt);
            return;
        }

        for (int i = next; i < charList.length; i++) {
            comb.add(charList[i]);
            findAnswer(r, cnt + 1, i + 1);
            comb.remove(comb.size() - 1);
        }
    }
}
