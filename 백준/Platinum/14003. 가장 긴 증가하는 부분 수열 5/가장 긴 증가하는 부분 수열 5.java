import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] idx = new int[N];
        ArrayList<Integer> ans = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            int tmp = arr[i];
            if (ans.size() == 0 || ans.get(ans.size() - 1) < tmp) {
                ans.add(tmp);
                idx[i] = ans.size() - 1;
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
                idx[i] = right;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ans.size()).append("\n");
        Stack<Integer> stack = new Stack<>();
        int index = ans.size()-1;
        for (int i = N-1; i >= 0; i--) {
            if (idx[i] == index) {
                stack.push(arr[i]);
                index--;
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }
}