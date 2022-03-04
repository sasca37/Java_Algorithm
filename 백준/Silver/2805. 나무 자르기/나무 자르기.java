import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int max = 0;
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }
        int min = 0;
        long total = 0;
        int center = 0;
        while(min <= max) {
            total = 0;
            center = (min + max) / 2;
            for (int x : arr) if (x > center) total += x-center;
            if (total >= M) { // center 값을 높인다.
                min = center+1;
            }
            else { // center 값을 낮춘다.
                max = center-1;
            }
        }
        System.out.println(max);
    }
}