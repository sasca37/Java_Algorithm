import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int idx = 1;
        while (true) {
            int num = 0;
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            if (L == 0 && P == 0 && V == 0) break;
            num += L * (V / P);
            int mod = V % P;
            if (mod > L) num += L;
            else num += mod;
            sb.append("Case ").append(idx).append(": ").append(num).append("\n");
            idx++;
        }
        System.out.println(sb);
    }
}