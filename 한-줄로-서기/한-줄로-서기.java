import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        Person[] persons = new Person[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            persons[i] = new Person(i+1, Integer.parseInt(st.nextToken()));
        }

        LinkedList<Person> order = new LinkedList<>();
        int step = 0;

        while(order.size() != N) {

            //계속해서 줄 세우기 위해 persons배열 순회
            for (int i = 0; i < N; i++) {
                //본인앞에 있어야하는 인원수가 충분히 존재한다면
                if (persons[i].higher <= step || persons[i].higher == 0) {
                    if (order.contains(persons[i]))
                        continue;
                    //현재 줄에 본인 higher기준 몇명이 더 큰지 cnt
                    int cnt = 0;
                    for (int j = 0; j < order.size(); j++) {
                        if (order.get(j).level > persons[i].level)
                            cnt++;
                    }

                    if (cnt == persons[i].higher) {
                        order.offer(persons[i]);
                        step++;
                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (Person p : order) {
            sb.append(p.level).append(" ");
        }

        System.out.println(sb);
    }

    static class Person {
        int level;
        int higher;

        public Person(int level, int higher) {
            this.level = level;
            this.higher = higher;
        }
    }
}