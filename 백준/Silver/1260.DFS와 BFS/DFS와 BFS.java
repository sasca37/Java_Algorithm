import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) arr.add(new ArrayList<>());

        int start = Integer.parseInt(st.nextToken())-1;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            arr.get(from).add(to);
            arr.get(to).add(from);
        }
        for (int i=0; i<N; i++) Collections.sort(arr.get(i));
        dfs(new boolean[N], start);
        sb.append("\n");
        bfs(start);
        System.out.println(sb);
    }

    private static void dfs(boolean[] visited, int start) {
        sb.append(start+1).append(" ");
        visited[start] = true;
        for (int x : arr.get(start)) {
            if (!visited[x]) {
                dfs(visited, x);
            }
        }
    }

    private static void bfs(int start) {
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current+1).append(" ");
            for (int x : arr.get(current)) {
                if (!visited[x]) {
                    queue.offer(x);
                    visited[x] = true;
                }
            }
        }
    }
}