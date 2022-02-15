import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int firstNum = firstStep(A,arr);
        secondStep(firstNum, B);
    }

    private static void secondStep(int n, int b) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            stack.push(n % b);
            n /= b;
        }
        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }

    private static int firstStep(int a, int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += Math.pow(a, arr.length-1-i) * arr[i];
        }
        return result;
    }

}