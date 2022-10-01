import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static int N, M;
    // 상 : -1, 0
    // 하 : +1, 0
    // 좌 : 0, -1
    // 우 : 0, +1

    static int[][] lists = {
            // 상 하 좌 우 좌상 좌하 우상
            {-1,0}, {+1,0}, {0, -1}, {0, +1}, {-1,-1}, {+1, -1}, {-1, +1}
    };

    // 상 : 0, 하 : 1, 좌 : 2, 우: 3, 좌상 : 4 , 좌하 : 5, 우상 :6
    static int[][] tetrominos = {
            {3, 1, 1},
            {0, 3, 3},
            {1, 1, 3},
            {1, 2, 2},
            {1, 1, 2},
            {1, 3, 3},
            {2, 1, 1},
            {3, 3, 1},
            {3, 3, 3},
            {1, 1, 1},
            {3, 1, 2},
            {1, 3, 1},
            {0, 3, 0},
            {3, 0, 3},
            {3, 1, 3},
            {3, 3, 5},
            {3, 3, 4},
            {1, 1, 4},
            {1, 1, 6}
    };
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
        int score = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < tetrominos.length; k++) {
                    boolean flag = true;
                    int subScore = board[i][j];
                    int nx = i;
                    int ny = j;
                    for (int t = 0; t < tetrominos[0].length; t++) {
                        int x = nx + lists[tetrominos[k][t]][0];
                        int y = ny + lists[tetrominos[k][t]][1];
                        if (isValid(x, y)) {
                            subScore += board[x][y];
                            nx = x;
                            ny = y;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) score = Math.max(score, subScore);
                }
            }
        }
        System.out.println(score);
    }

    private static boolean isValid(int x, int y) {
        if (x < 0 || y < 0 || x > N-1 || y > M-1) return false;
        return true;
    }
}