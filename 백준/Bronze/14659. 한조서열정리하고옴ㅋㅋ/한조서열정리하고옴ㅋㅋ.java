import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Deque<Integer> deque = new LinkedList<>();
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int tmp = arr[i];
            if(deque.isEmpty()) deque.addLast(tmp);
            else {
                int before = deque.peekFirst();
                if(before < tmp) {
                    answer = Math.max(answer, deque.size()-1);
                    deque.clear();
                }
                deque.addLast(tmp);
            }
        }
        answer = Math.max(answer, deque.size() - 1);
        System.out.println(answer);
    }
}