import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, arr[][];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            arr[from][to] = arr[to][from] = 1;
        }
        dfs(new boolean[N],start-1);
        sb.append("\n");
        bfs(start-1);
        System.out.println(sb);
    }

    private static void dfs(boolean[] visited, int start) {
        sb.append(start+1).append(" ");
        visited[start] = true;
        for (int i=0; i<N; i++) {
            if(!visited[i] && arr[start][i] != 0) {
                dfs(visited, i);
            }
        }
    }

    private static void bfs(int start) {
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current+1).append(" ");
            for (int i=0; i<N; i++) {
                if(!visited[i] && arr[current][i] != 0) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}
