import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, answer;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        visited[1] = true;
        dfs(1,0);
        if (answer % 2 == 0) System.out.println("No");
        else System.out.println("Yes");
    }

    private static void dfs(int node, int cnt) {

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, cnt + 1);
            }
        }

        if (node != 1 && graph.get(node).size() == 1) {
            answer += cnt;
        }
    }
}

