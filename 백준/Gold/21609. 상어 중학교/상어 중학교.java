import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, board[][], subBoard[][];
    static int ans;
    static boolean[][] visited;
    static PriorityQueue<Point> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while(true) {
            pq.clear();
            if (getBlockGroup() == 0) break;
            Point tmp = pq.poll();

            ans += Math.pow(tmp.totalBlock, 2);
            int num = board[tmp.x][tmp.y];
            board[tmp.x][tmp.y] = -9;

            removeDfs(tmp.x, tmp.y, num);
            getGravity();
            turnLeft();
            getGravity();
        }
        System.out.println(ans);
    }


    private static void turnLeft() {
        subBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                subBoard[i][j] = board[j][N-i-1];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = subBoard[i][j];
            }
        }
    }

    private static void getGravity() {
        for (int i = N-2; i >=0; i--) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] >= 0) {
                    startGravity(i,j);
                }
            }
        }
    }

    private static void startGravity(int x, int y) {
        int max = -1;
        for (int i=x+1; i<N; i++) {
            if(board[i][y] == -9) {
                max = Math.max(max, i);
            }
            else break;
        }
        if (max != -1) {
            int tmp = board[x][y];
            board[x][y] = -9;
            board[max][y] = tmp;
        }
    }


    private static void removeDfs(int x, int y, int num) {
        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isValid(nx,ny) && (board[nx][ny] == num || board[nx][ny] == 0)) {
                board[nx][ny] = -9;
                removeDfs(nx,ny,num);
            }
        }
    }

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static ArrayList<Point> points = new ArrayList<>();
    private static int getBlockGroup() {
        visited = new boolean[N][N];
        points.clear();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && board[i][j] > 0) {
                    blockCnt = 0;
                    rainbowCnt = 0;
                    visited[i][j] = true;
                    dfs(i,j, board[i][j]);
                    if(blockCnt+rainbowCnt > 1) {
                        cnt++;
                        pq.offer(new Point(i,j,blockCnt+rainbowCnt,rainbowCnt));
                    }
                    for (Point k : points) {
                        visited[k.x][k.y] = false;
                    }
                }
            }
        }
        return cnt;
    }
    static int blockCnt;
    static int rainbowCnt;
    private static void dfs(int x, int y, int num) {
        if(board[x][y] == 0) {
            rainbowCnt++;
            points.add(new Point(x,y));
        }
        else blockCnt++;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isValid(nx,ny) && !visited[nx][ny] && (board[nx][ny] == num || board[nx][ny] == 0)) {
                visited[nx][ny] = true;
                dfs(nx,ny,num);
            }
        }
    }


    private static boolean isValid(int x, int y) {
        if(x >= 0 && y >= 0 && x < N && y < N) return true;
        return false;
    }

}

class Point implements Comparable<Point>{
    int x,y, totalBlock, rainbows;
    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y, int totalBlock, int rainbows) {
        this.x = x;
        this.y = y;
        this.totalBlock = totalBlock;
        this.rainbows = rainbows;
    }

    @Override
    public int compareTo(Point o) {
        if (this.totalBlock == o.totalBlock) {
            if(this.rainbows == o.rainbows) {
                if (this.x == o.x) {
                    return o.y - this.y;
                }
                return o.x - this.x;
            }
            return o.rainbows - this.rainbows;
        }
        return o.totalBlock - this.totalBlock;
    }
}