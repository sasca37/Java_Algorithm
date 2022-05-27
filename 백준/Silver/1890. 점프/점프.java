import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] board;
    static long[][] dp;
    static int N;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        dp = new long[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 2; k++) {
                    if (board[i][j] == 0) break;
                    int nx = i + dx[k] * board[i][j];
                    int ny = j + dy[k] * board[i][j];
                    if (nx < 0 || ny < 0 || nx > N-1 || ny > N-1) continue;
                    dp[nx][ny] += dp[i][j];
                }
            }
        }
        System.out.println(dp[N-1][N-1]);
    }



}
