import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        String pattern = br.readLine();
        int cnt = 0;
        char first = pattern.charAt(0);
        for (int i = 0; i < word.length(); i++) {
            if(i+pattern.length()-1 < word.length() && word.charAt(i) == first) {
                boolean flag = true;
                for (int j = 1; j < pattern.length(); j++) {
                    if(word.charAt(i+j) != pattern.charAt(j)){
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    cnt++;
                    i += pattern.length()-1;
                }
            }
        }
        System.out.println(cnt);
    }
}