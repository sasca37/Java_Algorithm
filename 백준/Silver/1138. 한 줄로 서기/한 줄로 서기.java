import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        ArrayList<Integer> ans = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        for (int i = N - 1; i >= 0; i--) {
            int num = i + 1;
            int tmp = arr[i];
            if(ans.size() <= tmp) {
                ans.add(num);
            }
            else {
                ans.add(tmp, num);
            }
        }
        for (int x : ans) {
            sb.append(x).append(" ");
        }
        System.out.println(sb);
    }
}