import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] word = br.readLine().toCharArray();
        int answer = 0;
        for (int i = 0; i < word.length; i++) {
            if (i==0) {
                answer += 10;
                continue;
            }
            if (word[i-1] != word[i]) answer += 10;
            else answer += 5;
        }
        System.out.println(answer);
    }
}