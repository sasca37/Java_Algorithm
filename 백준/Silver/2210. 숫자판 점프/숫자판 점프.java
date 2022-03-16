import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] board;
    static HashSet<Integer> hashSet = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        board = new int[5][5];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(i,j,0,0);
            }
        }
        System.out.println(hashSet.size());
    }

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    private static void dfs(int x, int y, int total, int level) {
        if (level == 6) {
            if(!hashSet.contains(total)) hashSet.add(total);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < 5 && ny < 5 ) {
                dfs(nx, ny, total + (int) Math.pow(10, level) * board[nx][ny], level + 1);
            }
        }
    }
}