import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int answer = 1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int l = 0;
        int r = 0;
        int answer = 1;
        while (l <= r && r < N - 1) {
            if (arr[r] < arr[r + 1]) {
                r++;
            } else if (arr[r] == arr[r + 1]) {
                int t = 0;
                for (t = 0; t <= r - l; t++) {
                    if (r + 1 + t >= N || arr[r - t] != arr[r + 1 + t]) {
                        break;
                    }

                }
                answer = Math.max(answer, 2 * t);
                l = r + 1;
                r++;
            } else {
                int t = 0;
                for (t = 0; t < r - l; t++) {
                    if (r + 1 + t >= N || arr[r - 1 - t] != arr[r + 1 + t]) {
                        break;
                    }
                }
                answer = Math.max(answer, 2 * t + 1);
                l = r + 1;
                r += 1;
            }
        }
        System.out.println(answer);
    }
}