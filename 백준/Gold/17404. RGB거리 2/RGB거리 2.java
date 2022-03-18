import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] board,dp;
    static int N, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N+1][3+1];
        dp = new int[N+1][3+1];
        int red, green, blue;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            red = Integer.parseInt(st.nextToken());
            green = Integer.parseInt(st.nextToken());
            blue = Integer.parseInt(st.nextToken());
            board[i][1] = red;
            board[i][2] = green;
            board[i][3] = blue;
        }
        calcMinPrice();
    }
    // 1번 집은 2번, N번과 색이 다름 , N 번집은 N-1번, 1번 집과 색이 다름
    // i-1, i+1번 집의 색은 다름
    private static void calcMinPrice() {
        answer = Integer.MAX_VALUE;
        for (int start = 1; start <= 3; start++) {
            for (int color = 1; color <= 3; color++) {
                if (start == color) dp[1][color] = board[1][color];
                else dp[1][color] = 1001;
            }

            for (int i = 2; i <= N; i++) {
                dp[i][1] = Math.min(dp[i-1][2], dp[i-1][3]) + board[i][1];
                dp[i][2] = Math.min(dp[i-1][1], dp[i-1][3]) + board[i][2];
                dp[i][3] = Math.min(dp[i-1][1], dp[i-1][2]) + board[i][3];
            }
            if (start == 1) answer = Math.min(answer, Math.min(dp[N][2], dp[N][3]));
            else if (start == 2) answer = Math.min(answer, Math.min(dp[N][1], dp[N][3]));
            else answer = Math.min(answer, Math.min(dp[N][1], dp[N][2]));
        }
        System.out.println(answer);
    }
}