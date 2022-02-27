import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, board[][];
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Integer> ans = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = word.charAt(j)-'0';
            }
        }
        int cnt = 2;
        int n = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i,j,cnt);
                    cnt++;
                    n++;
                }
            }
        }
        sb.append(n).append("\n");
        int[] arr = new int[n];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    arr[board[i][j]-2]++;
                }
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<arr.length; i++) pq.offer(arr[i]);
        while(!pq.isEmpty()) sb.append(pq.poll()).append("\n");
        System.out.println(sb);
    }

    private static void dfs(int x, int y, int cnt) {
        board[x][y] = cnt;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx > N-1 || ny > N-1 || board[nx][ny] != 1) continue;
            dfs(nx,ny,cnt);
        }
    }
}