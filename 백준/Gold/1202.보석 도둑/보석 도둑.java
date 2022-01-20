import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    static int N, K;
    static PriorityQueue<Jewel> pq = new PriorityQueue();
    static int[] bags;
    static Jewel[] jewelries;
    static long answer = 0;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("SDS/firstweek/daythree/prob1202/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bags = new int[K];
        jewelries = new Jewel[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            jewelries[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(bf.readLine());
        }

        //가방 오름차순 정렬
        Arrays.sort(bags);

        Comparator<Jewel> comparator = new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        };

        //보석 무게 기준 오름차순 정렬
        Arrays.sort(jewelries, comparator);


        //가방 순회
        //가방에 들어갈 수 있는 보석들만 pq에 우선 넣어준다.
        //다 넣은 뒤에 pq에서 poll하고 정답에 더해줌. 가방이 오름차순으로 정렬되어있기 때문에 pq에 남아있는 보석들은
        //그 다음 크기의 가방들에 대해 넣을 수 있는 보석들이 보장됨.
        int jIndex = 0;

        for (int i = 0; i < bags.length; i++) {
            while (jIndex < jewelries.length) {
                int bagSize = bags[i];

                //가방크기 보다 보석의 무게가 작다면 pq에 넣어준다.
                if (bagSize >= jewelries[jIndex].weight) {
                    pq.offer(jewelries[jIndex++]);
                } else {
                    break;
                }
            }
            if (!pq.isEmpty())
                answer += pq.poll().price;
        }
        System.out.println(answer);
    }
}

class Jewel implements Comparable<Jewel>{
    public Jewel(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    int weight;
    int price;

    @Override
    public int compareTo(Jewel o) {
        return Integer.compare(o.price, this.price);
    }
}