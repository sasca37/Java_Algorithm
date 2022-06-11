import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] arr = {25, 10, 5, 1};
        for (int t = 0; t < T; t++) {
            int money = Integer.parseInt(br.readLine());
            int[] ans = new int[4];

            for (int i = 0; i < 4; i++) {
                if(money == 0) break;
                int num = arr[i];
                if (money >= num) {
                    ans[i] += money / num;
                    money %= num;
                }
            }

            for (int x : ans) sb.append(x).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}