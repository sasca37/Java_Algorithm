import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String word;
        char[] alphabets = new char[26];
        while ((word = br.readLine()) != null) {
            for (int i = 0; i < word.length(); i++) {
                char tmp = word.charAt(i);
                if(tmp != ' ') {
                    alphabets[tmp-97]++;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < alphabets.length; i++) {
            max = Math.max(max, alphabets[i]);
        }
        for (int i = 0; i < alphabets.length; i++) {
            if (alphabets[i] == max) {
                sb.append( (char)(i+97) );
            }
        }
        System.out.println(sb);
    }
}
