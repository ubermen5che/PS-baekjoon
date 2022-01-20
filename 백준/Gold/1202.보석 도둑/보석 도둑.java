import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Jewelry implements Comparable<Jewelry> {
        int price;
        int weight;

        public Jewelry(int w, int p) {
            super();
            this.weight = w;
            this.price = p;
        }

        @Override
        public int compareTo(Jewelry o) {
            return Integer.compare(weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Jewelry> list = new ArrayList<>();
        ArrayList<Integer> bagList = new ArrayList<>();
        int count = Integer.parseInt(st.nextToken());
        int bag = Integer.parseInt(st.nextToken());

        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            list.add(new Jewelry(w, p));
        }
        for (int i = 0; i < bag; i++) {
            bagList.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(bagList);
        Collections.sort(list);

        long rst = 0;
        int pt = 0;
        PriorityQueue<Jewelry> pq = new PriorityQueue<Jewelry>(new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                return o2.price - o1.price;
            }
        });

        for (Integer weight : bagList) {
            while (pt < list.size()) {
                if (list.get(pt).weight <= weight) {
                    pq.add(list.get(pt));
                } else {
                    break;
                }
                pt++;
            }
            if (!pq.isEmpty()) {
                int w = pq.poll().price;
                rst += w;
            }
        }
        System.out.print(rst);
    }
}