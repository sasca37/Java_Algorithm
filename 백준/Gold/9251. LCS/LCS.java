import java.io.*;
import java.util.*;

public class Main {
    static char[] wordA, wordB;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        wordA = br.readLine().toCharArray();
        wordB = br.readLine().toCharArray();

        dp = new int[wordA.length + 1][wordB.length + 1];
        for (int i = 1; i <= wordA.length; i++) {
            for (int j = 1; j <= wordB.length; j++) {
                if (wordA[i-1] == wordB[j-1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[wordA.length][wordB.length]);
    }
}