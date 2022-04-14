import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        if (a == b && b == c) {
            int ans = 10000 + a * 1000;
            System.out.println(ans);
        } else if (a==b || a==c) {
            int ans = 1000 + a * 100;
            System.out.println(ans);
        } else if (b==c) {
            int ans = 1000 + b * 100;
            System.out.println(ans);
        } else {
            int max = Math.max(a, Math.max(b,c));
            System.out.println(max * 100);
        }
    }
}