import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int R,C;
    static char[][] board;
    static boolean[][] visited;
    static boolean flag = false;
    static int answer = 0;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new boolean[R][C];
        for (int i=0; i<R; i++) {
            String word = br.readLine();
            for (int j=0; j<C; j++) {
                board[i][j] = word.charAt(j);
            }
        }
        for (int i=0; i<R; i++) {
            visited[i][0] = true;
            flag = false;
            backtracking(i,0);
        }
        System.out.println(answer);
    }

    private static void backtracking(int x, int y) {
        if(y == C-1) {
            flag = true;
            answer++;
            return;
        }

        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny <0 || nx > R-1 || ny > C-1 || visited[nx][ny] || board[nx][ny] == 'x') continue;
            if(flag) return;
            visited[nx][ny] = true;
            backtracking(nx,ny);
        }
    }
}