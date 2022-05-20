import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, east, west, north, south, board[][];
    static boolean[][] visited;
    static double[] direction;
    static double answer;
    // 동서남북 -> 우 좌 하 상
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        east = Integer.parseInt(st.nextToken());
        west = Integer.parseInt(st.nextToken());
        south = Integer.parseInt(st.nextToken());
        north = Integer.parseInt(st.nextToken());
        board = new int[30][30];
        visited = new boolean[30][30];
        direction = new double[]{ (double) east / 100, (double)west / 100, (double)south / 100, (double)north / 100};
        visited[15][15] = true;
        dfs(15,15,0,1.0);
        System.out.println(answer);
    }

    public static void dfs(int x, int y, int cnt, double ans) {
        if (cnt == N) {
            answer += ans;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx,ny, cnt+1, ans * direction[i]);
                visited[nx][ny] = false;
            }
        }
    }

}

