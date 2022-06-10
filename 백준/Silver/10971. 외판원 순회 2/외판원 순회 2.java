import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[] visited;
    static int[][] board;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i,i,0);
        }
        System.out.println(answer);
    }

    private static void dfs(int cur, int start, int weight) {
        if (weight > answer) return;
        visited[cur] = true;
        for (int i=0; i<N; i++) {
            if (i == cur) continue;
            if (!visited[i]) {
                if (board[cur][i] > 0) dfs(i, start, weight + board[cur][i]);
            }
            else if (chkVisited()) {
                if (i == start && board[cur][i] > 0) {
                    answer = Math.min(answer, weight + board[cur][i]);
                }
            }
        }
        visited[cur] = false;

    }

    private static boolean chkVisited() {
        for (int i = 0; i < N; i++) {
            if (!visited[i]) return false;
        }
        return true;
    }
}