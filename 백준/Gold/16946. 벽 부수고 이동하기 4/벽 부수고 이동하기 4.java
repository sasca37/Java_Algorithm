import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M, board[][], group[][], groupCnt;
    static int number;
    static boolean[][] visited;
    static ArrayList<Integer> groupList = new ArrayList<>();
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        group = new int[N][M];
        groupList.add(-9999);
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (int j = 0; j < M; j++) {
                int tmp = word.charAt(j)-'0';
                board[i][j] = tmp;
                if(tmp == 1) group[i][j] = -1;
                else group[i][j] = tmp;
            }
        }
        number = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (group[i][j] == 0 && !visited[i][j]) {
                    groupCnt = 1;
                    dfs(i,j);
                    number++;
                    groupList.add(groupCnt);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    int cnt = 1;
                    boolean[] chk = new boolean[groupList.size()+1];
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx < 0 || ny < 0 || nx > N-1 || ny > M-1) continue;
                        if (group[nx][ny] >= 1) {
                            if (!chk[group[nx][ny]]) {
                                chk[group[nx][ny]] = true;
                                cnt += groupList.get(group[nx][ny]);
                            }
                        }
                    }
                    sb.append(cnt % 10);
                }
                else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;
        group[x][y] = number;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx < 0 || ny < 0 || nx > N-1 || ny > M-1 || board[nx][ny] == 1 || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                groupCnt++;
                group[nx][ny] = number;
                queue.add(new Point(nx,ny));
            }
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

