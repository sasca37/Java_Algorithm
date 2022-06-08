import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] board;
    static boolean[][] visited;
    static int answer = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, L, R;
    static ArrayList<Point> arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move();
        System.out.println(answer);
    }


    private static void move() {
        boolean flag = true;
        arr = new ArrayList<>();
        while(flag) {
            int chk = 0;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx < 0 || ny < 0 || nx > N-1 || ny > N-1 || visited[nx][ny]) continue;
                        int num = Math.abs(board[i][j] - board[nx][ny]);
                        if (L <= num && num <= R) {
                            visited[i][j] = true;
                            chk++;
                            bfs(i,j);
                        }
                    }
                }
            }
            if (chk == 0) flag = false;
            else {
                answer++;
                for (Point tmp : arr) {
                    Stack<Point> stack = tmp.stack;
                    while (!stack.isEmpty()) {
                        Point x = stack.pop();
                        board[x.x][x.y] = tmp.totalNum;
                    }
                }
            }
        }

    }

    private static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        Stack<Point> stack = new Stack<>();
        queue.add(new Point(x, y));
        int total = board[x][y];
        stack.push(new Point(x, y));
        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx < 0 || ny <0 || nx > N-1 || ny > N-1 || visited[nx][ny]) continue;
                int num = Math.abs(board[tmp.x][tmp.y] - board[nx][ny]);
                if (L <= num && num <= R) {
                    total += board[nx][ny];
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                    stack.push(new Point(nx, ny));
                }
            }
        }

        int totalNum = total / stack.size();
        arr.add(new Point(stack, totalNum));
    }

    static class Point {
        int x,y;
        Stack<Point> stack;
        int totalNum;

        public Point(Stack<Point> stack, int totalNum) {
            this.stack = stack;
            this.totalNum = totalNum;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

