import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer = 1;
    static int N, dp[][];
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // N : 길이, 2 : 0 - 오름차순 길이, 1 - 내림차순 길이, 2: 현재 값
        dp = new int[N][3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            dp[i][2] = tmp;
            if(i==0) {
                dp[i][0] =1;
                dp[i][1] =1;
                continue;
            }
            else {
                dp[i][0] = (dp[i][2] >= dp[i-1][2])? dp[i-1][0]+1 : 1;
                dp[i][1] = (dp[i][2] <= dp[i-1][2])? dp[i-1][1]+1 : 1;
            }
            answer = Math.max(answer, Math.max(dp[i][0], dp[i][1]));
        }
        System.out.println(answer);
    }
}