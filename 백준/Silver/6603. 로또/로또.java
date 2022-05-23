import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, input[], numbers[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) break;
            input = new int[N];
            for (int i = 0; i < N; i++) input[i] = Integer.parseInt(st.nextToken());
            numbers = new int[6];
            dfs(0,0);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int start, int cnt) {
        if (cnt == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(numbers[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i < N; i++) {
            numbers[cnt] = input[i];
            dfs(i+1, cnt+1);
        }
    }
}

