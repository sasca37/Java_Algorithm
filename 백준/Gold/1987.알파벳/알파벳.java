import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int board[][];
    static int answer = 0;
    static int[] visited = new int[26];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        for (int i = 0; i < R; i++) {
            String word = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = word.charAt(j)-'A';
            }
        }
        backtracking(0,0,0);
        System.out.println(answer);
    }

    private static void backtracking(int x, int y,int cnt){
        if (visited[board[x][y]] == 1) {
            answer = Math.max(answer, cnt);
            return;
        }

        visited[board[x][y]] = 1;
        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx > R-1 || ny > C-1) continue;
            backtracking(nx,ny,cnt+1);
        }
        visited[board[x][y]] = 0;
    }
}