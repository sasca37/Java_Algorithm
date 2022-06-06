import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            sb.append("Case #");
            sb.append(t+1);
            sb.append(": ");
            st = new StringTokenizer(br.readLine());
            Stack<String> stack = new Stack<>();
            while (st.hasMoreTokens()) {
                stack.push(st.nextToken());
            }
            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}

