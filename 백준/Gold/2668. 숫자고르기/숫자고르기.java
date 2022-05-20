import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, current;
    static int[] board;
    static boolean[] visited;
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N+1];
        visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            board[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            current = i;
            dfs(i);
            visited[i] = false;
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for (int x : list) {
            sb.append(x).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int node) {
        if(board[node] == current) list.add(current);
        if (!visited[board[node]]) {
            visited[board[node]] = true;
            dfs(board[node]);
            visited[board[node]] = false;
        }
    }
}