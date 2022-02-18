import java.util.*;

public class Main {
    static int[][] graph;
    static int node, edge;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        node = sc.nextInt();
        edge = sc.nextInt();
        graph = new int[node+1][node+1];
        visited = new boolean[node+1];
        for (int i=0; i<edge; i++) {
            int start_node = sc.nextInt();
            int end_node = sc.nextInt();
            graph[start_node][end_node] = 1;
            graph[end_node][start_node] = 1;
        }
        int answer = 0;
        dfs(1,0);
        for (int i=2; i<visited.length; i++) {
            if(visited[i]) answer++;
        }
        System.out.println(answer);
    }

    private static void dfs(int start, int count) {
        visited[start] = true;
        count++;
        if(start == graph.length) return ;
        else {
            for(int i=1; i<graph.length; i++) {
                if(graph[start][i] == 1 && !visited[i]){
                    dfs(i,count);
                }
            }
        }
    }


}