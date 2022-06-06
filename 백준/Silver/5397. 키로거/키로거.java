import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String word = br.readLine();
            sb.append(getPassword(word)).append("\n");
        }
        System.out.println(sb);
    }

    private static String getPassword(String word) {
        Stack<Character> frontCursor = new Stack<>();
        Stack<Character> backCursor = new Stack<>();
        for (int i = 0; i < word.length(); i++) {
            char tmp = word.charAt(i);
            // back 을 꺼내서 front로
            if (tmp == '>') {
                if (!backCursor.isEmpty()) frontCursor.push(backCursor.pop());
            }
            // front를 꺼내서 back으로
            else if (tmp == '<') {
                if (!frontCursor.isEmpty()) backCursor.push(frontCursor.pop());
            }
            // front 삭제 - 커서 이전 문자 삭제이므로
            else if (tmp == '-') {
                if (!frontCursor.isEmpty()) frontCursor.pop();
            }
            else {
                frontCursor.push(tmp);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!backCursor.isEmpty()) {
            frontCursor.push(backCursor.pop());
        }

        for (int i = 0; i < frontCursor.size(); i++) {
            sb.append(frontCursor.elementAt(i));
        }
        return sb.toString();
    }
}

