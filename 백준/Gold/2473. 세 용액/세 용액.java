import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        long total = Long.MAX_VALUE;
        int a = 0, b = 0, c = 0;
        for (int i=0; i<N; i++) {
            int j = i+1;
            int k = N-1;
            while (j < k) {
                long sum = (long) arr[i] + arr[j] + arr[k];
                if (Math.abs(sum) < total) {
                    a = i;
                    b = j;
                    c = k;
                    total = Math.abs(sum);
                }
                if (sum < 0) j++;
                else k--;
            }
        }
        System.out.println(arr[a] +" " + arr[b] +" " + arr[c]);
    }
}