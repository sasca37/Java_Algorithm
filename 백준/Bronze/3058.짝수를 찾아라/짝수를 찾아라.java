import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int total = 0;
            int min = Integer.MAX_VALUE;
            while(st.hasMoreTokens()) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp % 2 == 0) {
                    min = Math.min(min, tmp);
                    total += tmp;
                }
            }
            sb.append(total).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
}