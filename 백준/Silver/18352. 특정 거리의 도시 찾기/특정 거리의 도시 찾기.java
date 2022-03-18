import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K, X,distance[];
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i=0; i<=N; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
        }
        distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        int start = X;
        distance[start] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(X);

        while (!pq.isEmpty()) {
            int tmp = pq.poll();
            for (int x : graph.get(tmp)) {
                if (distance[x] > distance[tmp] + 1) {
                    distance[x] = distance[tmp] + 1;
                    pq.offer(x);
                }
            }
        }
        int cnt = 0;
        for (int i=1; i<=N; i++) {
            if(distance[i] == K) {
                sb.append(i).append("\n");
                cnt++;
            }
        }
        System.out.println(cnt==0? -1 : sb.toString());
    }
}