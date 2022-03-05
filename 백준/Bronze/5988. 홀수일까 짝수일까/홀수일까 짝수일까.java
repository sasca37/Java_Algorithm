import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- >0) {
            String word = br.readLine();
            int num = word.charAt(word.length()-1);
            if (num % 2 == 0) sb.append("even").append("\n");
            else sb.append("odd").append("\n");
        }
        System.out.println(sb);
    }
}