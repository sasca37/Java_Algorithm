import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        int X = Integer.parseInt(br.readLine());
        int lt = 0;
        int rt = N-1;
        int ans = 0;
        while(lt < rt) {
            int num = arr[lt] + arr[rt];
            if (num == X) {
                ans++;
                lt++;
                rt--;
            }
            else if (num > X) {
                rt--;
            }
            else {
                lt++;
            }
        }
        System.out.println(ans);
    }
}