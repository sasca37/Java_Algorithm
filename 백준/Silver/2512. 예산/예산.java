import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int left = 0, right = -1;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, arr[i]);
        }

        int total = Integer.parseInt(br.readLine());
        while (left <= right) {
            int center = (left + right) / 2;
            int curTotal = 0;
            for (int i = 0; i < N; i++) {
                if (center > arr[i]) curTotal += arr[i];
                else curTotal += center;
            }
            if(curTotal <= total) left = center+1;
            else right = center-1;
        }
        System.out.println(right);
    }
}