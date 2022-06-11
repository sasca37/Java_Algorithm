import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String word = br.readLine();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = word.charAt(i) - '0';
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int tmp = arr[i];
            while(K > 0 && !stack.isEmpty()) {
                if(stack.peek() < tmp) {
                    stack.pop();
                    K--;
                }
                else break;
            }
            stack.push(tmp);

        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        String answer = sb.reverse().toString();
        if (K > 0) {
            answer = answer.substring(0, answer.length() - K);
        }
        System.out.println(answer);
    }
}