import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] graph;
    static int N, count;
    static boolean[] visited, isFinished;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            count = 0;
            visited = new boolean[N+1];
            isFinished = new boolean[N+1];
            st = new StringTokenizer(br.readLine());
            graph = new int[N+1];
            for (int i = 1; i <= N; i++) {
                graph[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <= N; i++) {
                dfs(i);
            }
            sb.append(N-count).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int node) {
        if(visited[node]) return;
        visited[node] = true;

        int nextNode = graph[node];
        if(!visited[nextNode]) {
            dfs(nextNode);
        } else {
            if (!isFinished[nextNode]) {
                count++;
                while (nextNode != node) {
                    count++;
                    nextNode = graph[nextNode];
                }
            }
        }
        isFinished[node] = true;
    }

}