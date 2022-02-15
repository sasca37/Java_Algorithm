import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int[] dx = new int[3];
        int[] dy = new int[3];
        for (int i=0; i<3; i++) {
            st = new StringTokenizer(br.readLine());
            dx[i] = Integer.parseInt(st.nextToken());
            dy[i] = Integer.parseInt(st.nextToken());
        }
        if (dx[0] == dx[1]) {
            sb.append(dx[2]).append(" ");
        }
        else if (dx[0] == dx[2]) {
            sb.append(dx[1]).append(" ");
        }
        else if (dx[1] == dx[2]) {
            sb.append(dx[0]).append(" ");
        }
        if (dy[0] == dy[1]) {
            sb.append(dy[2]);
        }
        else if (dy[0] == dy[2]) {
            sb.append(dy[1]);
        }
        else if (dy[1] == dy[2]) {
            sb.append(dy[0]);
        }
        System.out.println(sb);
    }
}