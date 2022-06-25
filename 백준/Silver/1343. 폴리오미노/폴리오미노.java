import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] word = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < word.length) {
            if (word[idx] == '.') {
                idx++;
                sb.append(".");
                continue;
            }
            if (isPossible('A', word, idx)) {
                sb.append("AAAA");
                idx += 4;
            } else if (isPossible('B',word, idx)) {
                sb.append("BB");
                idx += 2;
            } else {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(sb);
    }

    private static boolean isPossible(char alpha, char[] word, int idx) {
        if (alpha == 'A') {
            if (idx + 3 >= word.length) return false;
            for (int i = idx; i < idx + 4; i++) {
                if (word[i] != 'X') return false;
            }
            return true;
        } else {
            if (idx + 1 >= word.length) return false;
            for (int i = idx; i < idx + 2; i++) {
                if(word[i] != 'X') return false;
            }
        }
        return true;
    }
}