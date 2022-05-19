import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, root, ans;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i=0; i< N; i++) {
            graph.add(new ArrayList<>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp == -1) {
                root = i;
            }
            else {
                graph.get(tmp).add(i);
                graph.get(i).add(tmp);
            }
        }
        st = new StringTokenizer(br.readLine());
        int removeNode = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[N];
        visited[root] = true;
        visited[removeNode] = true;
        if (root == removeNode) {
            System.out.println(0);
            return;
        }
        dfs(root, visited,removeNode);
        System.out.println(ans);
    }

    private static void dfs(int node, boolean[] visited, int removeNode) {
        if (isNextNode(node, visited)) {
            for (int x : graph.get(node)) {
                if (!visited[x]) {
                    visited[x] = true;
                    dfs(x, visited, removeNode);
                }
            }
        }
        else ans++;
    }

    private static boolean isNextNode(int node, boolean[] visited) {
        boolean flag = false;
        for (int x : graph.get(node)) {
            if (!visited[x]) flag = true;
        }
        if(flag) return true;
        return false;
    }
}