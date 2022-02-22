import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());

        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();

        //노드 생성
        for (int i = 0; i < N; i++){
            graph.put(i+1, new ArrayList<>());
        }

        //노드 연결할 간선 입력(노드끼리 연결)
        for (int i = 0; i < M; i++){
            StringTokenizer stringTokenizer = new StringTokenizer(bf.readLine());
            Integer nodeA = Integer.parseInt(stringTokenizer.nextToken());
            Integer nodeB = Integer.parseInt(stringTokenizer.nextToken());

            graph.get(nodeA).add(nodeB);
            graph.get(nodeB).add(nodeA);
        }


        for (int i = 0; i < N; i++){
            Collections.sort(graph.get(i+1), Collections.reverseOrder());
        }
        dfsSearch(graph, startNode);

        for (int i = 0; i < N; i++){
            Collections.sort(graph.get(i+1));
        }
        bfsSearch(graph, startNode);
    }

    //dfs는 스택을 이용.
    public static void dfsSearch(HashMap<Integer, ArrayList<Integer>> graph, Integer start){

        ArrayList<Integer> stack = new ArrayList<>();
        ArrayList<Integer> visited = new ArrayList<>();
        String result = "";

        stack.add(start);

        while(!stack.isEmpty()){
            Integer removed = stack.remove(stack.size()-1);

            if (!visited.contains(removed)) {
                result += removed + " ";
                visited.add(removed);
                stack.addAll(graph.get(removed));
            }
        }
        result = result.substring(0, result.length()-1);
        System.out.print(result);
        System.out.println();
    }

    //queue를 이용.
    public static void bfsSearch(HashMap<Integer, ArrayList<Integer>> graph, Integer start){
        ArrayList<Integer> queue = new ArrayList<>();
        ArrayList<Integer> visited = new ArrayList<>();
        String result = "";

        queue.add(start);

        while(!queue.isEmpty()){
            Integer removed = queue.remove(0);

            if (!visited.contains(removed)) {
                visited.add(removed);
                queue.addAll(graph.get(removed));
                result += removed + " ";
            }
        }
        result = result.substring(0, result.length()-1);
        System.out.print(result);
        System.out.println();
    }
}