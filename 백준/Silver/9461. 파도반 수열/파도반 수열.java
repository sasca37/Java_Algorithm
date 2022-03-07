import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            long[] dp = new long[N+1];
            if (N <= 3) {
                sb.append(1).append("\n");
                continue;
            }
            dp[1]=1;
            dp[2]=1;
            dp[3]=1;
            for (int i = 4; i <= N; i++) {
                dp[i] = dp[i - 3] + dp[i - 2];
            }
            sb.append(dp[N]).append("\n");
        }
        System.out.println(sb);
    }
}
