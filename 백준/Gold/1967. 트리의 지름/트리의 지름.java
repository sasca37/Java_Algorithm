import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, index, max;
    static ArrayList<ArrayList<Point>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        for (int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Point(to, weight));
            graph.get(to).add(new Point(from, weight));
        }
        boolean[] visited = new boolean[N+1];
        dfs(1,0, visited);
        visited = new boolean[N+1];
        dfs(index,0, visited);
        System.out.println(max);
    }

    private static void dfs(int node, int weight, boolean[] visited) {
        visited[node] = true;
        if (weight > max) {
            max = weight;
            index = node;
        }

        for (Point x : graph.get(node)) {
            if (!visited[x.node]) {
                dfs(x.node, weight + x.w, visited);
            }
        }
    }

}
class Point {
    int node, w;

    public Point(int node, int w) {
        this.node = node;
        this.w = w;
    }
}