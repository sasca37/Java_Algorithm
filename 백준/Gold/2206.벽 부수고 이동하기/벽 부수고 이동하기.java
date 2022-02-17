import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
    static int N,M, board[][], visited[][];
    static int dx[] = {0,0, -1,1};
    static int dy[] = {-1,1, 0,0};
    static int answer = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            char arr[] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = arr[j]-'0';
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        bfs(0,0);
        System.out.println(answer);
    }

    private static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x,y,1,0));
        visited[x][y] = 0;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (p.x == N-1 && p.y == M-1) {
                answer = p.cnt;
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || ny < 0 || nx > N-1 || ny > M-1) continue;

                if (visited[nx][ny] <= p.wall) continue;

                if (board[nx][ny] == 0) {
                    visited[nx][ny] = p.wall;
                    queue.add(new Point(nx, ny, p.cnt + 1, p.wall));
                }
                else {
                    if (p.wall == 0) {
                        visited[nx][ny] = p.wall+1;
                        queue.add(new Point(nx,ny,p.cnt+1, p.wall+1));
                    }
                }
            }
        }
    }
}

class Point {
    int x,y, cnt, wall;

    public Point(int x, int y, int cnt, int wall) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.wall = wall;
    }
}