import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, board[][], cnt, max, ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    cnt++;
                    max = 1;
                    board[i][j] = 0;
                    dfs(i,j);
                    if (ans < max) ans = max;
                }
            }
        }
        System.out.println(cnt);
        System.out.println(ans);
    }

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    private static void dfs(int x, int y) {
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx >=0 && ny >=0 && nx < N && ny < M && board[nx][ny] == 1) {
                max++;
                board[nx][ny] = 0;
                dfs(nx,ny);
            }
        }
    }
}