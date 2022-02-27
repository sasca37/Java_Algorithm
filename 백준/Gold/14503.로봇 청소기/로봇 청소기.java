import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M, board[][], ans;
    // 북 동 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        Robot robot = new Robot(x,y,d,1);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs(robot);
        System.out.println(ans);
    }

    private static void bfs(Robot robot) {
        Queue<Robot> queue = new LinkedList<>();
        queue.offer(robot);
        board[robot.x][robot.y] = 2;
        while (!queue.isEmpty()) {
            Robot tmp = queue.poll();
//            for (int[] x : board) {
//                System.out.println(Arrays.toString(x));
//            }
//            System.out.println(tmp.cnt);
            boolean flag = false;
            for (int i = 1; i <= 4; i++) {
                int d = (tmp.d + 4 - i) % 4;
                int nx = tmp.x + dx[d];
                int ny = tmp.y + dy[d];
                if (nx < 0 || ny < 0 || nx > N-1 || ny > M-1 || board[nx][ny] != 0) continue;
                flag = true;
                board[nx][ny] = 2;
                queue.offer(new Robot(nx,ny,d,tmp.cnt+1));
                break;
            }
            if (!flag) {
                int nx = tmp.x + dx[tmp.d] * -1;
                int ny = tmp.y + dy[tmp.d] * -1;
                if (board[nx][ny] == 1) {
                    ans = tmp.cnt;
                    return;
                }
                else queue.offer(new Robot(nx,ny,tmp.d, tmp.cnt));
            }
        }


    }
}

class Robot {
    int x, y, d, cnt;

    public Robot(int x, int y, int d, int cnt) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.cnt = cnt;
    }
}