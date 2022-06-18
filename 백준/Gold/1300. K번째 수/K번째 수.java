import java.io.*;

public class Main {

    static int N, K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        long left = 1;
        long right = K;
        while (left < right) {
            long mid = (left + right) / 2;
            long total = getTotal(mid);
            if(K <= total) right = mid;
            else left = mid + 1;
        }
        System.out.println(left);
    }

    private static long getTotal(long m) {
        long total = 0;
        for (int i = 1; i <= N; i++) {
            total += Math.min(m / i, N);
        }
        return total;
    }
}