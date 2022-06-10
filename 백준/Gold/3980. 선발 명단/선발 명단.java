import java.io.*;
import java.util.*;

public class Main {
    static int[][] board;
    static boolean[] visited;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            board = new int[11][11];
            visited = new boolean[11];
            answer = 0;
            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(0,0);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int num, int total) {
        if (isFull()) {
            answer = Math.max(answer, total);
            return;
        }
        for (int i = 0; i < 11; i++) {
            if(board[num][i] != 0 && !visited[i]) {
                visited[i] = true;
                dfs(num+1, total + board[num][i]);
                visited[i] = false;
            }
        }
    }

    private static boolean isFull() {
        for (int i=0; i<11; i++) {
            if(!visited[i]) return false;
        }
        return true;
    }
}