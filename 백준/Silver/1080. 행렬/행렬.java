import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,M;
    static int board[][], subBoard[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        subBoard = new int[N][M];

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = word.charAt(j)-'0';
            }
        }

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (int j = 0; j < M; j++) {
                subBoard[i][j] = word.charAt(j)-'0';
            }
        }

        if (isSameBoard()) {
            System.out.println(0);
            return;
        }

        if (N < 3 || M < 3) {
            System.out.println(-1);
            return;
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isSameBoard()) {
                    System.out.println(answer);
                    return;
                }
                if(board[i][j] != subBoard[i][j]) {
                    changeBoard(i,j);
                    answer++;
                }
            }
        }


        System.out.println(-1);
    }

    private static boolean isSameBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(board[i][j] != subBoard[i][j]) return false;
            }
        }
        return true;
    }

    private static void changeBoard(int x, int y) {
        if (x+2 > N-1 || y+2 > M-1) return;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (board[i][j] == 0) board[i][j] = 1;
                else board[i][j] = 0;
            }
        }
    }

}
