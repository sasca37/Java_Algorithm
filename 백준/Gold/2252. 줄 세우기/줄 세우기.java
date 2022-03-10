import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 1 2 3 4 5 7
public class Main {
    static int[] edges;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        edges = new int[N+1];
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            edges[to]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i=1; i<=N; i++) {
            if (edges[i] == 0) {
                queue.offer(i);
                edges[i]--;
            }
        }
        while(!queue.isEmpty()) {
            int tmp = queue.poll();
            sb.append(tmp).append(" ");
            for (int i=0; i<graph.get(tmp).size(); i++) {
                int ptr = graph.get(tmp).get(i);
                edges[ptr]--;
                if (edges[ptr] == 0) queue.offer(ptr);
            }
        }
        System.out.println(sb);
    }
}