import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int W,B;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = new int[] {1, 1, 0, -1, -1, -1, 0, 1};
    static int[] dy = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] score = new int[] {0, 0, 1, 1, 2, 3, 5, 11};
    static TrieNode root = new TrieNode();
    static int sum, count;
    static String answer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        W = Integer.parseInt(br.readLine());

        for (int i = 0; i < W; i++) {
            //Trie만들기
            makeTrie(br.readLine());
        }

        br.readLine();

        B = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < B; i++) {
            map = new char[4][4]; //
            visited = new boolean[4][4];
            sum = 0;
            count = 0;
            answer = "";

            //4x4 그리드 주사위 채우기
            for (int j = 0; j < 4; j++) {
                String in = br.readLine();
                for (int k = 0; k < 4; k++) {
                    map[j][k] = in.charAt(k);
                }
            }

            //4x4 그리드 search 탐색범위는 루트노드에 자식이 있는 경우.
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (root.children[map[j][k] - 'A'] != null) { //자식이 존재하는경우 탐색
                        search(j, k, 1, root.children[map[j][k] - 'A']);
                    }
                }
            }
            if (i < B - 1)
                br.readLine(); //공백 라인입력받기

            result.append(sum).append(" ").append(answer).append(" ").append(count).append("\n");
            root.clearHit();
        }
        System.out.print(result);
    }

    static void makeTrie(String in) {
        TrieNode curNode = root;

        for (int i = 0; i < in.length(); i++) {
            int charIdx = in.charAt(i) - 'A';
            if (curNode.children[charIdx] == null)
                curNode.children[charIdx] = new TrieNode();
            curNode = curNode.children[charIdx];
        }
        curNode.isEnd = true;
    }
    static void search(int y, int x, int length, TrieNode curNode) {
        //1. 체크인
        visited[y][x] = true;
        sb.append(map[y][x]);
        //2. 목적지에 도달하였는가? -> isEnd, isHit
        if (curNode.isEnd && !curNode.isHit) {
            //정답 찍어내기
            curNode.isHit = true;
            sum += score[length-1];
            count++;
            //정답 문자열은 최대길이 이면서 사전순으로 오름차순이여야한다.
            if (compare(answer, sb.toString()) > 0) {
                answer = sb.toString();
            }
        }
        //3. 연결된 곳을 순회
        for (int i = 0; i < 8; i++) {
            int nY = y + dy[i];
            int nX = x + dx[i];
            //4. 가능한가? - map경계체크, 방문하지 않았는지, Node가 자식을 가지고 있는지
            if (nY >= 0 && nY < 4 && nX >= 0 && nX < 4) {
                if (!visited[nY][nX] && curNode.children[map[nY][nX] - 'A'] != null) {
                    //5.    간다.
                    search(nY, nX, length + 1, curNode.children[map[nY][nX] - 'A']);
                }
            }
        }
        //6. 체크아웃
        visited[y][x] = false;
        sb.deleteCharAt(length-1);
    }

    static int compare(String str1, String str2) {
        int result = Integer.compare(str2.length(), str1.length());

        if (result == 0) { //길이가 같을 경우 사전순으로 정답은 앞선것이여야함. 오름차순
            return str1.compareTo(str2);
        } else
            return result;
    }
    static class TrieNode {
        TrieNode[] children = new TrieNode[26]; // 알파벳 대문자 26개이므로 자식으로 최대 26개를 가질 수 있다.
        boolean isEnd; // Trie만들 때 찾고자 하는 단어 문자열 마지막에 end를 마킹해준다. (찾고자 하는 단어에 도달했는지 체킹위함
        boolean isHit; // 단어로 PRO, PROGRAMMER가 주어졌을 때 PRO라는 단어가 중복되어 찾아질 수 있다. 따라서 hit로 방문체크

        public boolean haveChild(char c) {
            return children[c - 'A'] != null;
        }

        void clearHit() {
            isHit = false;
            for (int i = 0; i < children.length; i++) {
                TrieNode child = children[i];
                if (child != null) {
                    child.clearHit();
                }
            }
        }
    }
}