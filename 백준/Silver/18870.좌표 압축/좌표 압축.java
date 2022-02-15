import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] word = new int[n];
        for (int i=0; i<n; i++) {
            word[i] = Integer.parseInt(input[i]);
        }
        int[] arr = word.clone();
        Arrays.sort(arr);

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int index = 0;
        for (int x : arr) {
            if (!hashMap.containsKey(x)) hashMap.put(x, index++);
        }
        StringBuilder sb = new StringBuilder();
        for (int x : word) {
            sb.append(hashMap.get(x)).append(' ');
        }
        System.out.println(sb);
    }
}