import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N,D, board[][];
    static Point center, start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            center = new Point(N/2, N/2);
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            if (D > 0) clockwise();
            else counterclockwise();

            for (int i=0; i<N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(board[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void clockwise() {
        for (int t=0; t< D/45; t++) {
            int start = 1;
            for (int i=center.x-1; i>=0; i--) {
                turnRightBoard(start++);
            }
        }
    }

    private static void counterclockwise() {
        for (int t=0; t< Math.abs(D)/45; t++) {
            int start = 1;
            for (int i=center.x-1; i>=0; i--) {
                turnLeftBoard(start++);
            }
        }

    }
    // 1. y 증가
    // 2. x 증가
    // 3. y 감소
    // 4. x 감소
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    private static void turnRightBoard(int n) {
        start = new Point(center.x-n, center.y-n);
        int idx = 0;
        int beforeNum = board[start.x][start.y];
        int currentNum = board[start.x][start.y];
        for (int i=0; i<4; i++) {
            for (int j=0; j<2; j++) {
                int nx = start.x + dx[idx] * n;
                int ny = start.y + dy[idx] * n;
                currentNum = board[nx][ny];
                board[nx][ny] = beforeNum;
                beforeNum = currentNum;
                start = new Point(nx,ny);
            }
            idx++;
        }
    }

    // 1. x 증가
    // 2. y 증가
    // 3. x 감소
    // 4. y 감소
    static int[] rdx = {1,0,-1,0};
    static int[] rdy = {0,1,0,-1};
    private static void turnLeftBoard(int n) {
        start = new Point(center.x-n, center.y-n);
        int idx = 0;
        int beforeNum = board[start.x][start.y];
        int currentNum = board[start.x][start.y];
        for (int i=0; i<4; i++) {
            for (int j=0; j<2; j++) {
                int nx = start.x + rdx[idx] * n;
                int ny = start.y + rdy[idx] * n;
                currentNum = board[nx][ny];
                board[nx][ny] = beforeNum;
                beforeNum = currentNum;
                start = new Point(nx,ny);
            }
            idx++;
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
