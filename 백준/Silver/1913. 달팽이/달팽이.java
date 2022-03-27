import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, board[][], ans, start;
    // 하 우 상 좌
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringBuilder ssb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        ans = Integer.parseInt(br.readLine());
        board = new int[N][N];
        start = N * N;
        turnBoard(0,0);
        for (int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] == ans) {
                    ssb.append(i+1).append(" ").append(j+1);
                }
                sb.append(board[i][j] +" ");
            }
            if (i==N-1) break;
            sb.append("\n");
        }
        System.out.println(sb);
        System.out.println(ssb);
    }

    private static void turnBoard(int x, int y) {
        int idx = 0;
        while(idx < 4) {
            board[x][y] = start--;
            if(start == 0) break;
            int nx = x + dx[idx];
            int ny = y + dy[idx];
            if (!isValid(nx,ny)) {
                if (idx == 3 && board[x][y] != 1) {
                    x = x+1;
                    idx = 0;
                }
                else {
                    idx++;
                    start++;
                }
            }
            else {
                x = nx;
                y = ny;
            }

        }
    }

    private static boolean isValid(int x, int y) {
        if (x < 0 || y < 0 || x > N-1 || y > N-1 || board[x][y] != 0) return false;
        return true;
    }
}
