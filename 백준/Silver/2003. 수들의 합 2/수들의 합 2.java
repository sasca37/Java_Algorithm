import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int ans = 0;
        int lt = 0;
        int rt = 0;
        while(rt < N) {
            int num = getNum(lt,rt);
            if (num == M) {
                ans++;
                lt++;
                rt++;
            }
            else if (num < M) {
                rt++;
            }
            else lt++;
        }
        System.out.println(ans);
    }

    private static int getNum(int a, int b) {
        int num = 0;
        for (int i=a; i<=b; i++) {
            num += arr[i];
        }
        return num;
    }
}