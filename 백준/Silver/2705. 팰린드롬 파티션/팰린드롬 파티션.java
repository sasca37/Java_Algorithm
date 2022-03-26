import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] dp = new int[1001];
        Arrays.fill(dp,1);
        dp[1] = 1;
        for (int i=2; i<=1000; i++) {
            for (int j = 0; j < i; j++) {
                if ((i-j) % 2 == 1)continue;
                dp[i] += dp[(i - j) / 2];
            }
        }
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N]+"\n");
        }
        System.out.println(sb);
    }
}