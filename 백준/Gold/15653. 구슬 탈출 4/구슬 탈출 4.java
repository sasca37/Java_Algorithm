import javax.crypto.spec.PSource;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static char[][] board;
    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[] dz = {'U', 'D', 'L', 'R'};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        Point red = null, blue = null;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char tmp = line.charAt(j);
                if (tmp == 'R') {
                    red = new Point(i, j);
                } else if (tmp == 'B') {
                    blue = new Point(i, j);
                }
                board[i][j] = tmp;
            }
        }

        Queue<Point[]> queue = new LinkedList<>();
        queue.add(new Point[]{red, blue});
        boolean[][][][] visited = new boolean[N][M][N][M];
        visited[red.x][red.y][blue.x][blue.y] = true;

        int cnt = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            ++cnt;
            for (int i = 0; i < size; i++) {
                Point[] tmp = queue.poll();

                for (int k = 0; k < 4; k++) {
                    Point r = new Point(tmp[0].x, tmp[0].y);
                    Point b = new Point(tmp[1].x, tmp[1].y);

                    boolean redFirst = isRedFirst(r, b, k);

                    move(r, k);
                    move(b, k);

                    if (board[b.x][b.y] == 'O') continue;
                    if (r.x == b.x && r.y == b.y) {
                        order(r, b, k, redFirst);
                    }

                    if (board[r.x][r.y] == 'O') {
                        System.out.println(cnt);
                        return;
                    }

                    if(visited[r.x][r.y][b.x][b.y]) continue;
                    visited[r.x][r.y][b.x][b.y] = true;

                    queue.add(new Point[]{r, b});
                }
            }
        }
        System.out.println(-1);

    }

    private static void order(Point r, Point b, int d, boolean redFirst) {
        switch (d) {
            case 0:
                if (redFirst) b.x++;
                else r.x++;
                break;
            case 1:
                if(redFirst) b.x--;
                else r.x--;
                break;
            case 2:
                if(redFirst) b.y++;
                else r.y++;
                break;
            case 3:
                if(redFirst) b.y--;
                else r.y--;
                break;
        }
    }

    private static void move(Point p, int d) {
        while (true) {
            int nx = p.x + dx[d];
            int ny = p.y + dy[d];
            if (board[nx][ny] == '#') break;
            p.x = nx;
            p.y = ny;
            if (board[nx][ny] == 'O') break;
        }
    }
    public static boolean isRedFirst(Point r, Point b, int d) {
        if ((d == 0 && r.x < b.x) || (d == 1 && r.x > b.x) || (d == 2 && r.y < b.y) || (d == 3 && r.y > b.y))
            return true;
        return false;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}