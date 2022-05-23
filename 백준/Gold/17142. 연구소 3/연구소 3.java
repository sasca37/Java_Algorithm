import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][] board, subBoard;
    static int[] numbers;
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Point> virus = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M];
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                board[i][j] = tmp;
                if (tmp == 2) {
                    virus.add(new Point(i,j));
                }
            }
        }
        combination(0, 0);
        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    private static void combination(int start, int cnt) {
        if (cnt == M) {
            answer = Math.min(answer, spreadVirus());
            return;
        }
        for (int i = start; i < virus.size(); i++) {
            numbers[cnt] = i;
            combination(i+1, cnt+1);
        }
    }

    private static int spreadVirus() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int time = 0;
        for (int x : numbers) {
            queue.add(virus.get(x));
            visited[virus.get(x).x][virus.get(x).y] = true;
        }
        makeSubBoard();
        if(isBoardAllClear()) return 0;
        ArrayList<Point> nextVirus = new ArrayList<>();
        while (!isBoardAllClear()) {
            if (time >= answer) return time;
            if (!nextVirus.isEmpty()) {
                for (Point x : nextVirus) {
                    queue.add(x);
                }
                nextVirus = new ArrayList<>();
            }
            boolean isPossible = false;
            while (!queue.isEmpty()) {
                Point tmp = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = tmp.x + dx[i];
                    int ny = tmp.y + dy[i];
                    if (nx >= 0 && ny >= 0 && nx <= N-1 && ny <= N-1 && (subBoard[nx][ny] ==0 || (!visited[nx][ny] && subBoard[nx][ny] >= 2) )) {
                        visited[nx][ny] = true;
                        nextVirus.add(new Point(nx,ny));
                        isPossible = true;
                        subBoard[nx][ny] = subBoard[tmp.x][tmp.y] + 1;
                    }
                }
            }
            time++;
            if (!isPossible) return Integer.MAX_VALUE;
        }
        return time;
    }

    private static void makeSubBoard() {
        subBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                subBoard[i][j] = board[i][j];
            }
        }
    }

    private static boolean isBoardAllClear() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(subBoard[i][j] == 0) return false;
            }
        }
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