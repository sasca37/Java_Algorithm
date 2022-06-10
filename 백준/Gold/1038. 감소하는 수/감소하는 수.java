import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Long> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if (N > 1022) System.out.println(-1);
        else {
            for (int i = 0; i < 10; i++) {
                dfs(i, 1);
            }
            Collections.sort(answer);
            System.out.println(answer.get(N));
        }
    }

    private static void dfs(long num, int level) {
        if(level > 10) return;
        answer.add(num);
        for (int i = 0; i < num % 10; i++) {
            dfs(num * 10 + i, level+1);
        }
    }
}