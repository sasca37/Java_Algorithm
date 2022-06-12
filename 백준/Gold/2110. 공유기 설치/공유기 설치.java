import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static int N,C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int row = 1;
        int high = arr[N-1] - arr[0] + 1;

        while (row < high) {
            int mid = (high + row) / 2;
            if (getCenter(mid) < C) {
                high = mid;
            }
            else {
                row = mid +1;
            }
        }
        System.out.println(row -1);
    }

    private static int getCenter(int c) {
        int count = 1;
        int lastLocate = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int locate = arr[i];
            if(locate - lastLocate >= c) {
                count++;
                lastLocate = locate;
            }
        }
        return count;
    }
}