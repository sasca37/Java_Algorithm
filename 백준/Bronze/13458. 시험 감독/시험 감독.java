import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int main = Integer.parseInt(st.nextToken());
        int sub = Integer.parseInt(st.nextToken());
        long ans = 0;
        for (int i=0; i<N; i++) {
            int tmp = arr[i];
            if (tmp <= main) {
                ans++;
            } else {
                ans++;
                tmp -= main;
                if (tmp % sub == 0) ans += tmp / sub;
                else ans += tmp / sub +1;
            }
        }
        System.out.println(ans);
    }
}