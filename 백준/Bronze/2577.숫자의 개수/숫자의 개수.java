import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        int k = a * b * c;
        String t = k +"";
        int[] arr = new int[10];
        for (int i = 0; i < t.length(); i++) {
            arr[t.charAt(i)-'0']++;
        }
        for (int x : arr) System.out.println(x);
    }
}