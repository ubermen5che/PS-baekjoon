import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static ArrayList<Integer> tmp = new ArrayList<>();
    static Set<Integer> set = new HashSet<>();
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(sc.nextLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(sc.nextLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // Unique보장하기 위해 set에 넣음
        for (int i = 0; i < N; i++) {
            set.add(arr[i]);
        }

        Iterator iter = set.iterator();
        while(iter.hasNext()) {
            tmp.add((Integer) iter.next());
        }

        Collections.sort(tmp);

        for (int i = 0; i < N; i++) {
            int idx = bSearch(arr[i]);
            sb.append(idx + " ");
        }

        System.out.println(sb);
    }

    static int bSearch(int target) {
        int left = 0;
        int right = tmp.size() - 1;
        int mid = 0;

        while(left <= right) {
            mid = (left + right) / 2;
            if (tmp.get(mid) == target)
                return mid;

            if (tmp.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return mid;
    }
}