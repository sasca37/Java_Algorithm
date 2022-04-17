import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] W = new int[10];
        int[] K = new int[10];
        for (int i = 0; i < 20; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (i < 10) W[i] = tmp;
            else K[i-10] = tmp;
        }
        Arrays.sort(W);
        Arrays.sort(K);
        int ansW = W[9] + W[8] + W[7];
        int ansK = K[9] + K[8] + K[7];
        System.out.println(ansW +" " + ansK);
    }
}