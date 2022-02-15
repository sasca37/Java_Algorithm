import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static Deque<Integer> deque = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            String word = br.readLine();
            if (word.contains(" ")) {
                StringTokenizer st = new StringTokenizer(word);
                String a = st.nextToken();
                int cnt = Integer.parseInt(st.nextToken());
                command(a,cnt);
            }
            else command(word);
        }
        System.out.println(sb);
    }

    private static void command(String word) {
        if(word.equals("pop_front")) {
            if(deque.isEmpty()) sb.append(-1).append("\n");
            else sb.append(deque.pollFirst()).append("\n");
        }
        else if(word.equals("pop_back")) {
            if(deque.isEmpty()) sb.append(-1).append("\n");
            else sb.append(deque.pollLast()).append("\n");
        }
        else if(word.equals("size")) sb.append(deque.size()).append("\n");
        else if(word.equals("empty")) {
            if (deque.isEmpty()) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }
        else if(word.equals("front")) {
            if (deque.isEmpty()) sb.append(-1).append("\n");
            else sb.append(deque.getFirst()).append("\n");
        }
        else {
            if (deque.isEmpty()) sb.append(-1).append("\n");
            else sb.append(deque.getLast()).append("\n");
        }
    }
    private static void command(String word, int n) {
        if(word.equals("push_front")) {
            deque.addFirst(n);
        }
        else deque.addLast(n);
    }
}
