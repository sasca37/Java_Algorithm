import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        for (int i = 1; i <= N; i++) {
            if (dp[i] != Integer.MAX_VALUE) {
                int jump = arr[i];
                for (int j = 1; j <= jump; j++) {
                    if (i+j > N) continue;
                    dp[i+j] = Math.min(dp[i]+1, dp[i+j]);
                }
            }
        }
        System.out.println(dp[N] == Integer.MAX_VALUE?-1:dp[N]);
    }
}
