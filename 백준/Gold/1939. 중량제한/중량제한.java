import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static ArrayList<ArrayList<Island>> graph = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        int left = 0, right = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Island(B, C));
            graph.get(B).add(new Island(A, C));
            right = Math.max(right, C);
        }
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        while (left <= right) {
            int mid = (left + right) / 2;
            boolean[] visited = new boolean[N+1];
            if(bfs(from, to, mid, visited)) {
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        System.out.println(right);
    }

    private static boolean bfs(int from, int to, int mid, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(from);
        visited[from] = true;
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            if (tmp == to) return true;
            for (Island next : graph.get(tmp)) {
                if (!visited[next.to] && next.weight >= mid) {
                    visited[next.to] = true;
                    queue.add(next.to);
                }
            }
        }
        return false;
    }

    static class Island {
        int to, weight;

        public Island(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}