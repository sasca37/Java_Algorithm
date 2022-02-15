import java.io.*;

public class Main {
    static int[][] arr = new int[30][30];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char tmp = word.charAt(i);
            if ('a' <= tmp && tmp<= 'z') {
                char pos = (char) (tmp+13);
                if (pos > 'z') pos = (char)(pos-26);
                sb.append(pos);
            }
            else if ('A' <= tmp && tmp <= 'Z') {
                char pos = (char) (tmp+13);
                if (pos > 'Z') pos = (char)(pos-26);
                sb.append(pos);
            }
            else sb.append(tmp);
        }
        System.out.println(sb);
    }
}