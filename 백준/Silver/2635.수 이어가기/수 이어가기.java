import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int answer = 0;
    static int idx = -1;
    static String[] word;
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        word = new String[N+1];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.setLength(0);
            recur(N, i, 1,i,sb);
        }
        System.out.println(answer);
        System.out.println(word[idx]);
    }

    private static void recur(int beforeNum, int num, int cnt, int index, StringBuilder sb) {
        sb.append(beforeNum).append(" ");
        if (num < 0) {
            if (answer < cnt) idx = index;
            answer = Math.max(answer, cnt);
            word[index] = sb.toString();
            return;
        }
        recur(num, beforeNum-num, cnt+1, index, sb);
    }
}