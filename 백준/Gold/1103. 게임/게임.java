import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, board[][], max, dp[][];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean flag = false;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (int j = 0; j < M; j++) {
                char tmp = word.charAt(j);
                if (tmp == 'H') {
                    board[i][j] = -1;
                } else {
                    board[i][j] = tmp - '0';
                }
            }
        }
        dfs(0,0,1);
        if(flag) System.out.println(-1);
        else System.out.println(max);
    }

    private static void dfs(int x, int y, int cnt) {
        if(flag) return;
        if (cnt > max) max = cnt;
        dp[x][y] = cnt;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * board[x][y];
            int ny = y + dy[i] * board[x][y];
            if (nx < 0 || ny < 0 || nx > N-1 || ny > M-1 || board[nx][ny] == -1) continue;
            if (visited[nx][ny]) {
                flag = true;
                return;
            }

            if (dp[nx][ny] > cnt) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, cnt + 1);
            visited[nx][ny] = false;
        }
    }
}
