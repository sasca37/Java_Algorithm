import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int ans,N;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
//        bfs(1);
        boolean[] visited = new boolean[N+1];
        visited[1] = true;
        dfs(1,  visited);

        System.out.println(ans);
    }

    private static void dfs(int start, boolean[] visited) {
        for (int x : graph.get(start)) {
            if(!visited[x]) {
                ans++;
                visited[x] = true;
                dfs(x, visited);
            }
        }
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int x : graph.get(vertex)) {
                if(!visited[x]) {
                    visited[x] = true;
                    ans++;
                    queue.offer(x);
                }
            }
        }
    }
}