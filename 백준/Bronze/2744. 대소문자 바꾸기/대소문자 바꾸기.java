import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char tmp = word.charAt(i);
            if(65 <= tmp && tmp <= 90) {
                tmp += 32;
            }
            else {
                tmp -= 32;
            }
            sb.append(tmp);
        }
        System.out.println(sb);
    }

}

