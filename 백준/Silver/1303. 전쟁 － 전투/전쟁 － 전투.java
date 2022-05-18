import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char board[][];
    static boolean visited[][];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int wCnt, bCnt, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cnt = 0;
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i,j);
                    if (board[i][j] == 'W') {
                        wCnt += cnt * cnt;
                    } else {
                        bCnt += cnt * cnt;
                    }
                }
            }
        }
        System.out.println(wCnt +" " + bCnt);
    }

    private static void dfs(int x, int y) {
        cnt++;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx > N-1 || ny > M-1 || nx < 0 || ny < 0 || visited[nx][ny] || board[nx][ny] != board[x][y]) continue;
            visited[nx][ny] = true;
            dfs(nx,ny);
        }
    }
}
