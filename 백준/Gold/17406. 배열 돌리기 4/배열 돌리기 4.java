import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] board, subBoard;
    static ArrayList<Point> points = new ArrayList<>();
    static int[] numbers;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        subBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                subBoard[i][j] = board[i][j];
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            points.add(new Point(R, C, S));

        }

        numbers = new int[points.size()];
        boolean[] visited = new boolean[points.size()];
        permutation(0, visited);

        System.out.println(ans);

    }

    private static void permutation(int level, boolean[] visited) {
        if (level == points.size()) {
            for (int x : numbers) {
                int startX = points.get(x).r - points.get(x).s-1;
                int startY = points.get(x).c - points.get(x).s-1;
                int endX = points.get(x).r + points.get(x).s-1;
                int N = endX - startX +1;
                while (N > 0) {
                    dfs(startX, startY, N);
                    N-=2;
                    startX++;
                    startY++;
                }
            }
            for (int i = 0; i < N; i++) {
                int total = 0;
                for (int j = 0; j < M; j++) {
                    total += board[i][j];
                }
                ans = Math.min(ans, total);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    board[i][j] = subBoard[i][j];
                }
            }
            return;
        }
        for (int i = 0; i < points.size(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                numbers[level] = i;
                permutation(level+1, visited);
                visited[i] = false;
            }
        }
    }

    static class Point{
        int r, c, s;

        public Point(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    // 우 하 좌 상
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    private static void dfs(int x, int y, int n) {
        int before = board[x][y];
        int cur = board[x][y];
        int nx = x;
        int ny = y;
        int idx = 0;
        while (idx < 4) {
            if (isValid(nx,ny, x,y,n,idx)) {
                nx += dx[idx];
                ny += dy[idx];
                cur = board[nx][ny];
                board[nx][ny] = before;
                before = cur;
            }
            else idx++;
        }
    }

    private static boolean isValid(int nx, int ny, int x, int y, int n, int idx) {
        nx += dx[idx];
        ny += dy[idx];
        if (nx < x || ny < y || nx > x+n-1 || ny > y+n-1) return false;
        return true;
    }
}