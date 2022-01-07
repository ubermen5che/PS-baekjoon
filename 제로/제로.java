import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Integer> numList = new LinkedList<>();

        int K = Integer.parseInt(bf.readLine());
        int sum = 0;
        int num;

        for (int i = 0; i < K; i++) {
            num = Integer.parseInt(bf.readLine());
            if (num != 0)
                numList.offer(num);
            else
                numList.pollLast();
        }

        while(!numList.isEmpty())
            sum += numList.poll();

        System.out.println(sum);
    }
}