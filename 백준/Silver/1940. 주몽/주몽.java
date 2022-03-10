import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 1 2 3 4 5 7
public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        int lt = 0;
        int rt = N-1;
        int ans = 0;
        while (lt < rt) {
            int num = arr[lt] + arr[rt];
            if (num == M) {
                ans ++;
                lt++;
                rt--;
            }
            else if (num < M) {
                lt ++;
            }
            else rt--;
        }
        System.out.println(ans);
    }
}