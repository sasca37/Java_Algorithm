import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, max, answer[];
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = new int[N+1];
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N+1];
            visited[i] = true;
            bfs(i);
        }

        for (int i = 1; i <= N; i++) {
            if (max < answer[i])  max = answer[i];
        }
        for (int i = 1; i <= N; i++) {
            if (answer[i] == max) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    private static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            for (int next : graph.get(tmp)) {
                if (!visited[next]) {
                    visited[next] = true;
                    answer[next]++;
                    bfs(next);
                }
            }
        }
    }
}