import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static char[][] board;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int wolf,sheep;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = word.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && board[i][j] != '#') {
                   bfs(i,j);
                }
            }
        }
        System.out.println(sheep+" "+wolf);
    }

    private static void bfs (int x, int y) {
        Queue<Point> queue =  new LinkedList<>();
        queue.offer(new Point(x,y));
        visited[x][y] = true;

        int w=0;
        int s=0;
        while(!queue.isEmpty()) {
            Point tmp = queue.poll();
            if(board[tmp.x][tmp.y] == 'o') {
                s++;
            } else if (board[tmp.x][tmp.y] == 'v') {
                w++;
            }
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x+dx[i];
                int ny = tmp.y + dy[i];
                if (isValid(nx,ny) && !visited[nx][ny] && board[nx][ny] != '#') {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx,ny));
                }
            }
        }
        if (w < s) sheep+=s;
        else wolf+=w;
    }

    private static boolean isValid(int x, int y) {
        if (x < 0 || y < 0 || x > N-1 || y > M-1) return false;
        return true;
    }
}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}