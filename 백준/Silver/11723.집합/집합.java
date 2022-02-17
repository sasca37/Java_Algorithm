import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int bit = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int num = 0;
            if(!command.equals("all") && !command.equals("empty")){
                num = Integer.parseInt(st.nextToken());
            }
            if (command.equals("add")) {
                bit = bit | (1 << num);
            }
            else if (command.equals("remove")) {
                bit = bit &~(1<<num);
            }
            else if (command.equals("check")) {
                if ( (bit & (1 << num)) > 0) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            }
            else if (command.equals("toggle")) {
                bit = bit^(1<<num);
            }
            else if (command.equals("all")) {
                bit = (1 << 21) - 1;
            }
            else bit = 0;
        }
        System.out.println(sb);
    }
}
