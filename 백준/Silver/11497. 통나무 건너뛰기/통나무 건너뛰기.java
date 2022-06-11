import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            int[] answer = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(arr);
            int idx = 0;
            int flag = 0;
            for (int i = 0; i < N / 2; i++) {
                answer[idx] = arr[flag++];
                answer[N - 1 - idx] = arr[flag++];
                idx++;
            }
            if(N % 2 == 1) {
                answer[N/2] = arr[N-1];
            }
            int sum = 0;
            for (int i = 0; i < N - 1; i++) {
                sum = Math.max(sum, Math.abs(answer[i]-answer[i+1]));
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}