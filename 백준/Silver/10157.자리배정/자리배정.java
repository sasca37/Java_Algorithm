import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 상 우 하 좌
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] board;
    static boolean[][] visited;
    static int C,R, K;
    static int a, b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        visited = new boolean[R][C];
        K = Integer.parseInt(br.readLine());
        if (K > R * C) {
            System.out.println(0);
            return;
        }
        start(R-1, 0 , 1);
        System.out.println(a+" "+b);
    }

    private static void start(int x, int y, int number) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y, number));
        while (!q.isEmpty()) {
            Point tmp = q.poll();
            if (tmp.number == K) {
                a = tmp.y+1;
                b = R-tmp.x;
                return;
            }
            board[tmp.x][tmp.y] = tmp.number;
            visited[tmp.x][tmp.y] = true;
            for (int i=0; i<4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx < 0 || ny < 0 || nx > R-1 || ny > C-1 || visited[nx][ny]) continue;
                if (i == 0) {
                    int lx = tmp.x + dx[3];
                    int ly = tmp.y + dy[3];
                    if (lx >= 0 && ly >= 0 && lx <= R-1 && ly <= C-1 && !visited[lx][ly]) {
                        nx = lx;
                        ny = ly;
                    }
                }
                q.offer(new Point(nx, ny, tmp.number+1));
                break;
            }
        }
    }
}

class Point {
    int x, y, number;

    public Point(int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
    }
}