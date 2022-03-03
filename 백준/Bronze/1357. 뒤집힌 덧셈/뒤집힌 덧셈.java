import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String X = st.nextToken();
        String Y = st.nextToken();
        StringBuilder sb = new StringBuilder(X);
        StringBuilder ssb = new StringBuilder(Y);
        int total = Integer.parseInt(sb.reverse().toString()) + Integer.parseInt(ssb.reverse().toString());
        sb.setLength(0);
        sb.append(total+"");
        System.out.println(Integer.parseInt(sb.reverse().toString()));
    }
}