import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, board[][];
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static boolean isFinish = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = word.charAt(j) - '0';
            }
        }

        for (int i = 0; i < M; i++) {
            if (board[0][i] == 1) continue;
            if(!isFinish) bfs(0,i);
            else {
                System.out.println("YES");
                return;
            }
        }
        if(isFinish) System.out.println("YES");
        else System.out.println("NO");
    }

    private static void bfs(int x, int y) {
        boolean[][] visited = new boolean[N][M];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx == N-1) {
                    isFinish = true;
                    return;
                }
                if (nx < 0 || ny < 0 || nx > N-1 || ny > M-1 || visited[nx][ny] || board[nx][ny] == 1) continue;
                visited[nx][ny] = true;
                queue.add(new Point(nx,ny));
            }
        }
    }
}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
