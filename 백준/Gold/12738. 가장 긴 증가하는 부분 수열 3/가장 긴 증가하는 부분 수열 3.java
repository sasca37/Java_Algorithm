import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        ArrayList<Integer> ans = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            int tmp = arr[i];
            if (ans.size() == 0 || ans.get(ans.size() - 1) < tmp) {
                ans.add(tmp);
            } else {
                int left = 0;
                int right = ans.size()-1;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (ans.get(mid) >= tmp) {
                        right = mid;
                    } else left = mid + 1;
                }
                ans.set(right, tmp);
            }
        }
        System.out.println(ans.size());
    }
}