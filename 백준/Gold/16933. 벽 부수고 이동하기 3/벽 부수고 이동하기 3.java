import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] board;
    static boolean[][][] visited;
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M][11];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = row.charAt(j) - '0';
            }
        }
        visited[0][0][0] = true;
        bfs();
        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    private static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 0, 1, true));
        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            if (tmp.x == N-1 && tmp.y == M-1) {
                answer = tmp.total;
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx <0 || ny < 0 || nx > N-1 || ny > M-1 ) continue;
                if (board[nx][ny] == 0 && !visited[nx][ny][tmp.k]) {
                    visited[nx][ny][tmp.k] = true;
                    queue.add(new Point(nx, ny, tmp.k, tmp.total + 1, !tmp.isMorning));
                }
                if (board[nx][ny] == 1 && tmp.k < K && !visited[nx][ny][tmp.k + 1]) {
                    if (tmp.isMorning) {
                        visited[nx][ny][tmp.k + 1] = true;
                        queue.add(new Point(nx, ny, tmp.k + 1, tmp.total + 1, !tmp.isMorning));
                    }
                    else queue.add(new Point(tmp.x, tmp.y, tmp.k,tmp.total+1, !tmp.isMorning));
                }
            }
        }
    }

    static class Point {
        int x,y, k, total;
        boolean isMorning;

        public Point(int x, int y, int k, int total, boolean isMorning) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.total = total;
            this.isMorning = isMorning;
        }
    }
}

