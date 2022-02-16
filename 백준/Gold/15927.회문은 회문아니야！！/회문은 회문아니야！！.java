import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] word = br.readLine().toCharArray();
        boolean flag = false;
        for (int i = 0; i < word.length/2; i++) {
            if (word[i] != word[word.length-1-i]) {
                System.out.println(word.length);
                return;
            }
            if (word[i] != word[i+1]) flag = true;
        }
        if (flag) {
            System.out.println(word.length-1);
        }
        else System.out.println("-1");
    }
}