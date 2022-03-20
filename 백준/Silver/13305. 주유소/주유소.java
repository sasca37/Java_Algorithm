import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] distances = new long[N-1];
        long[] prices = new long[N];
        long ans = 0;
        long totalDis = 0;
        for (int i = 0; i < N - 1; i++) {
            distances[i] = Long.parseLong(st.nextToken());
            totalDis += distances[i];
        }
        st = new StringTokenizer(br.readLine());
        long min = Long.MAX_VALUE;
        for (int i = 0; i < N-1; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            prices[i] = tmp;
        }
        for (int i=0; i<N-1; i++) {
           if (prices[i] < min) min = prices[i];
           ans += min * distances[i];
        }
        System.out.println(ans);
    }
}