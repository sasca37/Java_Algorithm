import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        while (n != 0) {
            if (n % k < 10) sb.append(n % k);
            else sb.append(chk(n%k));
            n /= k;
        }
        System.out.println(sb.reverse());
    }

    private static char chk(int n) {
        char result = (char) (n+55);
        return result;
    }
}
