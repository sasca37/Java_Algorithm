import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0,-1, board);
        System.out.println(answer);
    }

    private static void up(int[][] board) {
        for (int i = 0; i < N-1; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    for (int k = i + 1; k < N; k++) {
                        if (board[k][j] != 0) {
                            board[i][j] = board[k][j];
                            board[k][j] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void down(int[][] board) {
        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    for (int k = i - 1; k >= 0; k--) {
                        if (board[k][j] != 0) {
                            board[i][j] = board[k][j];
                            board[k][j] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void left(int[][] board) {
        for (int j = 0; j < N - 1; j++) {
            for (int i = 0; i < N; i++) {
                if (board[i][j] == 0) {
                    for (int k = j + 1; k < N; k++) {
                        if(board[i][k] != 0) {
                            board[i][j] = board[i][k];
                            board[i][k] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void right(int[][] board) {
        for (int j = N - 1; j > 0; j--) {
            for (int i = 0; i < N; i++) {
                if (board[i][j] == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        if (board[i][k] != 0) {
                            board[i][j] = board[i][k];
                            board[i][k] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void dfs(int level, int d, int[][] board) {
        if (level == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    answer = Math.max(answer, board[i][j]);
                }
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int[][] makeBoard = getBoard(i, board);
            dfs(level + 1, i, makeBoard);
        }
    }

    private static int[][] getBoard(int d, int[][] board) {
        int[][] makeBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                makeBoard[i][j] = board[i][j];
            }
        }
        // 상
        if (d == 0) {
            up(makeBoard);
            for (int i = 0; i < N - 1; i++) {
                for (int j = 0; j < N; j++) {
                    if (makeBoard[i][j] == makeBoard[i + 1][j]) {
                        makeBoard[i][j] *= 2;
                        makeBoard[i+1][j] = 0;
                        up(makeBoard);
                    }
                }
            }
        } else if (d == 1) {
            down(makeBoard);
            for (int i = N - 1; i > 0; i--) {
                for (int j = 0; j < N; j++) {
                    if (makeBoard[i][j] == makeBoard[i - 1][j]) {
                        makeBoard[i][j] *=2;
                        makeBoard[i-1][j] = 0;
                        down(makeBoard);
                    }
                }
            }
        } else if (d == 2) {
            left(makeBoard);
            for (int j = 0; j < N - 1; j++) {
                for (int i = 0; i < N; i++) {
                    if (makeBoard[i][j] == makeBoard[i][j+1]) {
                        makeBoard[i][j] *= 2;
                        makeBoard[i][j+1] = 0;
                        left(makeBoard);
                    }
                }
            }
        } else if (d == 3) {
            right(makeBoard);
            for (int j = N - 1; j > 0; j--) {
                for (int i = 0; i < N; i++) {
                    if (makeBoard[i][j] == makeBoard[i][j - 1]) {
                        makeBoard[i][j] *= 2;
                        makeBoard[i][j-1] = 0;
                        right(makeBoard);
                    }
                }
            }
        }


        return makeBoard;
    }
}