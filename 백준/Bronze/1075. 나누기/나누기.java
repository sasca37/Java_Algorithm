import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int F = Integer.parseInt(br.readLine());
        int start = N - (N % 100);
        for (int i = start; i <= start + 99; i++) {
            int tmp = i % F;
            if (tmp == 0) {
                String ans = i + "";
                System.out.println(ans.substring(ans.length()-2, ans.length()));
                break;
            }
        }
    }
}