import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N, board[][];
    static boolean ans = false;
    static HashSet<Point> hs = new HashSet<>();
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                char tmp = st.nextToken().charAt(0);
                if(tmp == 'X') board[i][j] = 0; // 빈칸
                else if (tmp == 'S')  board[i][j] = 1; // 학생
                else {
                    board[i][j] = -1;  // 선생
                    hs.add(new Point(i,j));
                }
            }
        }
        dfs(0);
        if(ans) System.out.println("YES");
        else System.out.println("NO");
    }

    private static void dfs(int level) {
        if (level == 3) {
            chkStudentEscape();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = -9; // 장애물
                    dfs(level+1);
                    board[i][j] = 0;
                }
            }
        }
    }

    private static void chkStudentEscape() {
        for (Point tmp : hs) {
            int x = tmp.x;
            int y = tmp.y;
            if(findStudent(x,y)) return;
        }
        ans = true;
    }

    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    private static boolean findStudent(int x, int y) {
        int idx;
        for (int i = 0; i < 4; i++) {
            idx = 1;
            while (true) {
                int nx = x + dx[i] * idx;
                int ny = y + dy[i] * idx;
                if (nx < 0 || ny < 0 || nx > N-1 || ny > N-1) { // 벽을 넘어갔으면
                    break;
                }
                else if (board[nx][ny] == 1) { // 학생 발견 시
                    return true;
                }
                else if (board[nx][ny] == -9 || board[nx][ny] == -1) { // 장애물이나 선생을 만나면
                    break;
                }
                else idx++;
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
