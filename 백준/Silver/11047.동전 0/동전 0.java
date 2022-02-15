import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,K, coins[];
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N];
        for (int i=0; i<N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        chkMinCnt();
        System.out.println(answer);
    }

    private static void chkMinCnt() {
        for (int i=N-1; i>=0; i--) {
            if(K >= coins[i]) {
                answer += K / coins[i];
                K %= coins[i];
                if (K == 0) return;
            }
        }
    }
}