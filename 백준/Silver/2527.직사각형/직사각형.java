import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<4; t++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x3 = Integer.parseInt(st.nextToken());
            int y3 = Integer.parseInt(st.nextToken());
            int x4 = Integer.parseInt(st.nextToken());
            int y4 = Integer.parseInt(st.nextToken());

            if(x1 > x4 || x2 < x3 || y1 > y4 || y2 < y3) sb.append("d").append("\n");
            else if((x1 == x4 && y1 == y4) || (x2 == x3 && y1 == y4) || (x1 == x4 && y2 == y3) || (x2 == x3 && y2 == y3)) sb.append("c").append("\n");
            else if((x1 == x4 && y1 != y4) || (x2 != x3 && y1 == y4) || (x1 != x4 && y2 == y3) || (x2 == x3 && y2 != y3)) sb.append("b").append("\n");
            else sb.append("a").append("\n");
        }
        System.out.println(sb);
    }
}