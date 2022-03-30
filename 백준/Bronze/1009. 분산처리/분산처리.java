import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            if(a % 10 == 0) sb.append("10").append("\n");
            else if(b == 1) sb.append(a % 10).append("\n");
            else {
                long tmp = a;
                for (int j=1; j<b; j++) {
                    a *= tmp;
                    a %= 10;
                }
                sb.append(a).append("\n");
            }
        }
        System.out.println(sb);
    }
}