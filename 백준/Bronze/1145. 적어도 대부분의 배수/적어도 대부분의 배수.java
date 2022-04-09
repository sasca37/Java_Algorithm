import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[5];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            arr[i] = tmp;
            min = Math.min(min, tmp);
        }
        int ans = 0;
        outer :
        for (int i = min + 1; i <= 1000000; i++) {
            int cnt = 0;
            for (int j = 0; j < 5; j++) {
                if (i % arr[j] == 0) cnt++;
            }
            if (cnt > 2) {
                ans = i;
                break outer;
            }
        }
        System.out.println(ans);
    }
}