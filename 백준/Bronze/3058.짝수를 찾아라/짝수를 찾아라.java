import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            int total = 0;
            while(st.hasMoreTokens()) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp % 2 == 0) {
                    queue.offer(tmp);
                    total += tmp;
                }
            }
            sb.append(total).append(" ").append(queue.poll()).append("\n");
        }
        System.out.println(sb);
    }
}