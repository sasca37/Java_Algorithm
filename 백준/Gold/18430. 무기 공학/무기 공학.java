import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int board[][];
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0,0, new boolean[N][M]);
        System.out.println(answer);
    }

    private static void dfs(int x, int y, int total, boolean[][] visited) {
        if (x == N - 1) {
            answer = Math.max(answer, total);
            return;
        }
        boolean copy[][] = new boolean[N][M];
        for (int i = 0; i < 5; i++) {
            for (int j=0; j<N; j++) {
                copy[j] = Arrays.copyOf(visited[j], M);
            }
            int curSum = getSum(i, x,y, copy);
            if (curSum > -1) {
                int nx, ny;
                if (y == M - 1) {
                    nx = x + 1;
                    ny = 0;
                } else {
                    nx = x;
                    ny = y + 1;
                }
                dfs(nx,ny,total + curSum, copy);
            }
        }
    }

    // 현재 0 상1 하2 좌3 우4 좌상5 우상6 하좌7 하우8
    static int[] dx = {0, -1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, 0, -1, 1, -1, 1, -1, 1};
    static int[][] types = {
            // 우,현재위치(가운데), 하
            {4, 0, 2},
            // 현재위치, 하(가운데), 하우
            {0, 2, 8},
            // 현재위치, 하(가운데), 하좌
            {0, 2, 7},
            // 현재위치, 우(가운데), 우하
            {0, 4, 8},
            {0}
    };
    private static int getSum(int type, int x, int y, boolean[][] visited) {
        int[] points = types[type];
        if (points.length == 1) return 0;
        for (int i = 0; i < 3; i++) {
            int curX = x + dx[types[type][i]];
            int curY = y + dy[types[type][i]];
            if (!valid(curX, curY) || visited[curX][curY] ) return -1;
        }
        int total = 0;
        for (int i = 0; i < 3; i++) {
            int curX = x + dx[types[type][i]];
            int curY = y + dy[types[type][i]];
            visited[curX][curY] = true;
            if (i == 1 ) total += board[curX][curY] * 2;
            else total += board[curX][curY];
        }
        return total;
    }

    private static boolean valid(int x, int y) {
        if (x < 0 || y < 0 || x > N-1 || y > M-1) return false;
        return true;
    }

}