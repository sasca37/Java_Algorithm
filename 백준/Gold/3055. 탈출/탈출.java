import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R,C;
    static char[][] board;
    static boolean[][] visited;
    static Point hedgehog;
    static Point nest;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                char tmp = row.charAt(j);
                board[i][j] = tmp;
                if (tmp == 'S') hedgehog = new Point(i, j);
                else if (tmp == 'D') nest = new Point(i, j);
            }
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(hedgehog.x, hedgehog.y, 0));
        visited[hedgehog.x][hedgehog.y] = true;
        spreadWater();
        int idx = 0;
        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            if (tmp.x == nest.x && tmp.y == nest.y) {
                answer = tmp.time;
                break;
            }
            if (tmp.time > idx) {
                spreadWater();
                idx++;
            }
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx < 0 || ny < 0 || nx > R-1 || ny > C-1 || visited[nx][ny] || board[nx][ny] == '*' || board[nx][ny] == 'X') continue;
                visited[nx][ny] = true;
                queue.add(new Point(nx, ny, tmp.time + 1));
            }
        }

        if (answer == Integer.MAX_VALUE) System.out.println("KAKTUS");
        else System.out.println(answer);
    }

    private static void spreadWater() {
        ArrayList<Point> waters = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(board[i][j] == '*') waters.add(new Point(i, j));
            }
        }

        for (Point water : waters) {
            for (int k = 0; k < 4; k++) {
                int nx = water.x + dx[k];
                int ny = water.y + dy[k];
                if (nx < 0 || ny < 0 || nx > R-1 || ny > C-1 || board[nx][ny] != '.')continue;
                board[nx][ny] = '*';
            }
        }
    }

    static class Point {
        int x,y, time;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}

