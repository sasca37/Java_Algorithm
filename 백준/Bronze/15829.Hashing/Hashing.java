import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int R = 31;
    static final int M = 1234567891;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String word = br.readLine();
        long ans = 0;
        long r = 1;
        for (int i = 0; i < N; i++) {
            int tmp = word.charAt(i) - 'a'+1;
            ans += (tmp * r) % M;
            r =  (r  * R) % M;
        }
        ans %= M;
        System.out.println(ans);
    }
}