import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        ArrayList<Integer> numList = new ArrayList<>();
        ArrayList<Integer> answerList = new ArrayList<>();
        HashMap<Integer, Integer> numFrequency = new HashMap<>();

        for (int i = 0; i < N; i++) {
            numList.add(Integer.parseInt(bf.readLine()));
            if (!numFrequency.containsKey(numList.get(i))) {
                numFrequency.put(numList.get(i), 0);
            } else {
                int tmp = numList.get(i);
                numFrequency.replace(tmp, numFrequency.get(tmp)+1);
            }
        }

        Collections.sort(numList);

        //산술 평균
        int sum = 0;
        for (int i = 0; i < numList.size(); i++) {
            sum += numList.get(i);
        }

        answerList.add(Math.round((float)sum / numList.size()));

        //중앙값 (1,2,3,4,5)
        answerList.add(numList.get(numList.size() / 2));

        //최빈값 (num frequency에는 각 element에 해당하는 빈도수 저장)
        //최빈값 탐색
        int maxFreq = Integer.MIN_VALUE;

        for (Integer key : numFrequency.keySet()) {
            maxFreq = Math.max(maxFreq, numFrequency.get(key));
        }

        ArrayList<Integer> maxFreqKeySet = new ArrayList<>();

        for (Integer key : numFrequency.keySet()) {
            if (maxFreq == numFrequency.get(key))
                maxFreqKeySet.add(key);
        }

        Collections.sort(maxFreqKeySet);

        if (maxFreqKeySet.size() == 1) {
            answerList.add(maxFreqKeySet.get(0));
        } else if (maxFreqKeySet.size() > 1) {
            answerList.add(maxFreqKeySet.get(1));
        }

        answerList.add(numList.get(numList.size()-1) - numList.get(0));

        for (Integer i : answerList)
            System.out.println(i);
    }
}