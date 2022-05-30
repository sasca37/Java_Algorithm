import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, L;
    static int[][] board;
    static Point[] directions;
    // 우 하 좌 상
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) -1;
            int y = Integer.parseInt(st.nextToken()) -1;
            board[x][y] = -2;
        }
        L = Integer.parseInt(br.readLine());
        directions = new Point[L];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            directions[i] = new Point(time, dir);
        }

        int ans = bfs(0, 0, 0, 0);
        System.out.println(ans);
    }

    private static int bfs(int x, int y, int time, int d) {
        Queue<Point> queue = new LinkedList<>();
        Deque<Point> tails = new LinkedList<>();
        tails.add(new Point(0, 0));
        queue.add(new Point(x, y, time, d));
        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            if (tmp.x < 0 || tmp.y <0 || tmp.x > N-1 || tmp.y > N-1 || board[tmp.x][tmp.y] == -1) {
                return tmp.time;
            }

            if(tmp.time == 0) {
                board[0][0] = -1;
                int nx = tmp.x + dx[d];
                int ny = tmp.y + dy[d];
                queue.add(new Point(nx,ny,tmp.time+1, d));
                continue;
            }

            tails.addFirst(new Point(tmp.x, tmp.y));
            if (board[tmp.x][tmp.y] != -2) {
                Point tail = tails.pollLast();
                board[tail.x][tail.y] = 0;
            }

            board[tmp.x][tmp.y] = -1;

            int direction = chkTurnDirection(tmp.time, tmp.d);
            int nx = tmp.x + dx[direction];
            int ny = tmp.y + dy[direction];
            queue.add(new Point(nx,ny,tmp.time+1, direction));

        }
        return 0;
    }

    private static int chkTurnDirection(int time, int d) {
        for (Point x : directions) {
            if (x.time == time) {
                if (x.dir == 'L') {
                    d -= 1;
                    if (d == -1) d = 3;
                } else {
                    d += 1;
                    if (d == 4) d = 0;
                }
                return d;
            }
        }
        return d;
    }

    static class Point {
        int x, y;
        int time;
        char dir;
        int d;

        public Point(int x, int y, int time, int d) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.d = d;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }
}
