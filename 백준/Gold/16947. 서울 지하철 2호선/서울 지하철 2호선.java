import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            if(dfs(i,i,i)) break;
            visited = new boolean[N + 1];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (visited[i]) sb.append(0).append(" ");
            else sb.append(bfs(i)).append(" ");
        }
        System.out.println(sb);
    }

    private static int bfs(int n) {
        Queue<int[]> queue = new LinkedList<>();
        boolean visit[] = new boolean[N+1];
        visit[n] = true;
        queue.add(new int[]{n, 0});
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            if(visited[tmp[0]]) return tmp[1];
            for (int next : graph.get(tmp[0])) {
                if(!visit[next]) {
                    visit[next] = true;
                    queue.add(new int[]{next, tmp[1] + 1});
                }
            }
        }
        return -1;
    }

    private static boolean dfs(int before, int cur, int start) {
        visited[cur] = true;
        for (int next : graph.get(cur)) {
            if(!visited[next]) {
                if (dfs(cur,next,start)) return true;
            }
            else if (before != next && next == start) {
                return true;
            }
        }
        visited[cur] = false;
        return false;
    }

}