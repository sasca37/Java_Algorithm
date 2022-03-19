import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(br.readLine());
            stack.push(tmp);
        }
        int ans = 1;
        int max = stack.pop();
        while (!stack.isEmpty()) {
            int x = stack.pop();
            if (max < x) {
                ans ++;
                max = x;
            }
        }
        System.out.println(ans);
    }
}