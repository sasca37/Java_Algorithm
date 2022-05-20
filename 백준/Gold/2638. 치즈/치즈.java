import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M, board[][], time;
    static boolean[][] visited;
    static ArrayList<Point> cheeseList;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

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
        while(isContainsCheese()){
            time++;
            visited = new boolean[N][M];
            visited[0][0] = true;
            makeOutSide(0,0);
            cheeseList = new ArrayList<>();
            isMeltExpect();
            removeCheeseAndResetBoard();
        }
        System.out.println(time);
    }

    private static void removeCheeseAndResetBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    for (Point x : cheeseList) {
                        if (x.x == i && x.y == j) {
                            board[i][j] = 0;
                        }
                    }
                }
                else if (board[i][j] == -1) board[i][j] = 0;
            }
        }
    }

    private static void isMeltExpect() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx < 0 || ny < 0 || nx > N-1 || ny > M-1 || board[nx][ny] != -1) continue;
                        cnt++;
                    }
                    if (cnt >= 2) cheeseList.add(new Point(i,j));
                }
            }
        }
    }

    private static void makeOutSide(int x, int y) {
        board[x][y] = -1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx > N-1 || ny > M-1 || visited[nx][ny] || board[nx][ny] != 0) continue;
            visited[nx][ny] = true;
            makeOutSide(nx,ny);
        }
    }

    private static boolean isContainsCheese() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) return true;
            }
        }
        return false;
    }
}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}