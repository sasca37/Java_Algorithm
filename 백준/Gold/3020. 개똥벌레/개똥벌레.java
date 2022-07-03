import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] bottom = new int[H + 2];
        int[] top = new int[H + 2];

        for (int i = 0; i < N / 2; i++) {
            bottom[Integer.parseInt(br.readLine())]++;
            top[H - Integer.parseInt(br.readLine()) + 1]++;

        }
        for (int i = 1; i <= H; i++) {
            bottom[i] += bottom[i - 1];
        }
        for (int i = H; i >= 1; i--) {
            top[i] += top[i + 1];
        }

        int min = N;
        int cnt = 0;
        for (int i = 1; i <= H; i++) {
            int cur = bottom[H] - bottom[i - 1] + top[1] - top[i + 1];
            if (cur < min) {
                min = cur;
                cnt = 1;
            } else if (min == cur) {
                cnt++;
            }
        }
        System.out.println(min +" " + cnt);
    }
}