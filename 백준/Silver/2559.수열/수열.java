import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        int answer = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            arr[i] = arr[i - 1] + tmp;
        }
        for (int i=0; i<=N-K; i++) {
            answer = Math.max(arr[i+K] - arr[i], answer);
        }
        System.out.println(answer);
    }
}
