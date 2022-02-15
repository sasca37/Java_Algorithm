import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 북 기준 우하좌상 * 2
    // 1이면 0 시작 , 2이면 2, 3이면 3, 4이면 1
    static int[] dx = {0,1,0,-1,0,1,0,-1};
    static int[] dy = {1,0,-1,0,1,0,-1,0};
    static int[][] board;
    static int X, Y, D;
    static int N,M;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[N+1][M+1];
        int command = Integer.parseInt(br.readLine());
        for (int i = 0; i < command; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int position = Integer.parseInt(st.nextToken());
            chkPosition(direction, position, false);
        }
        st = new StringTokenizer(br.readLine());
        int storeDirection = Integer.parseInt(st.nextToken());
        int storePosition = Integer.parseInt(st.nextToken());
        chkPosition(storeDirection, storePosition, true);
        startCalc();
//        for (int[] xx : board) {
//            for (int x : xx) {
//                System.out.print(x + " ");
//            }
//            System.out.println();
//        }
        System.out.println(answer);
    }

    private static void startCalc() {
        int idx = D;
        int distance = 0;
        int totalLen = (N + M) * 2;
        while(idx < D+5) {
            int nx = X + dx[idx];
            int ny = Y + dy[idx];
            if (nx < 0 || ny < 0 || nx > N || ny > M) idx++;
            else {
                distance++;
                if(distance == totalLen) return;
                X = nx;
                Y = ny;
                if(board[nx][ny] == 1) {
                    answer += Math.min(distance, totalLen-distance);
                }
            }
        }
    }

    private static void chkPosition(int d, int p, boolean isStore) {
        if (d==1) {
            if (isStore) {
                X = 0;
                Y = p;
                D = 0;
            }
            else board[0][p] = 1;
        }
        else if (d==2) {
            if (isStore) {
                X = N;
                Y = p;
                D = 2;
            }
            else board[N][p] = 1;
        }
        else if (d==3) {
            if (isStore) {
                X = p;
                Y = 0;
                D = 3;
            }
            else board[p][0] = 1;
        }
        else {
            if (isStore) {
                X = p;
                Y = M;
                D = 1;
            }
            else board[p][M] = 1;
        }
    }
}