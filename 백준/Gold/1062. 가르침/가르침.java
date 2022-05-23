import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, answer, flag;
    static String[] words;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new String[N];
        /*
            a c i n t 5개 필수
        */
        if (K < 5) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words[i] = word.substring(4, word.length() - 4);

        }
        flag = 0;
        flag |= 1 << (0);
        flag |= 1 << ('n'-'a');
        flag |= 1 << ('t'-'a');
        flag |= 1 << ('i'-'a');
        flag |= 1 << ('c'-'a');
        combination(0,0,flag);
        System.out.println(answer);
    }

    private static void combination(int cnt, int start, int flag) {
        if (cnt == K - 5) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                boolean isValid = true;
                for (int j = 0; j < words[i].length(); j++) {
                    if ( (flag & 1 << (words[i].charAt(j)-'a')) == 0) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid) count++;
            }
            answer = Math.max(answer, count);
            return;
        }
        for (int i = start; i < 26; i++) {
            if ( (flag & 1 << i) != 0) continue;
            combination(cnt+1, i+1, flag | 1 << i);
        }
    }
}

