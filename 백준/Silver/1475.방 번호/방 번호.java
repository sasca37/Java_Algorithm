import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[10];
        String word = br.readLine();
        for (int i = 0; i < word.length(); i++) {
            arr[word.charAt(i)-'0']++;
        }
        int mean = (arr[6] + arr[9]) % 2 == 0 ? (arr[6] + arr[9]) / 2 : (arr[6] + arr[9]) / 2 + 1;
        arr[6] = arr[9] = mean;
        int max = Integer.MIN_VALUE;
        for (int i=0; i<10; i++) max = Math.max(max, arr[i]);
        System.out.println(max);
    }
}