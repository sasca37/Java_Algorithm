import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] word = br.readLine().toCharArray();
        System.out.println(chkWord(word));
    }

    private static int chkWord(char[] word) {
        Stack<Character> stack = new Stack<>();
        int pointer = 1;
        int answer = 0;
        for (int i = 0; i < word.length; i++) {
            char tmp = word[i];
            if (tmp == '(') {
                pointer *=2;
                stack.push(tmp);
            } else if (tmp == '[') {
                pointer *= 3;
                stack.push(tmp);
            } else if (tmp ==')') {
                if (stack.isEmpty() || stack.peek() != '(') return 0;
                if (word[i-1] == '(') {
                    answer += pointer;
                }
                pointer /= 2;
                stack.pop();
            } else {
                if (stack.isEmpty() || stack.peek() != '[') return 0;
                if (word[i-1] == '[') {
                    answer += pointer;
                }
                pointer /= 3;
                stack.pop();
            }
        }
        if (!stack.isEmpty()) return 0;
        else return answer;
    }
}
