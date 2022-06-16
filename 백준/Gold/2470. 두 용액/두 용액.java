import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int left = 0, right = N-1;
        Arrays.sort(arr);
        int l=0,r=0;
        int cur;
        int max = Integer.MAX_VALUE;
        while (left < right) {
            int total = arr[left] + arr[right];
            cur = Math.abs(total);
            if (cur < max) {
                max = cur;
                l = arr[left];
                r = arr[right];
            }
            if (total > 0) right--;
            else left++;
        }
        System.out.println(l +" " + r);
    }
}