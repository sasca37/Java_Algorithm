import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        for (int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        visited[1] = true;
        dfs(1, 0);
        for (boolean x : visited) {
            if (x) ans++;
        }
        System.out.println(ans-1);
    }

    private static void dfs(int n, int level) {

        if(level == 2) return;
        for (int x : graph.get(n))  {
            if(!visited[x] || level < 2) {
                visited[x] = true;
                dfs(x, level+1);
            }

        }
    }
}

