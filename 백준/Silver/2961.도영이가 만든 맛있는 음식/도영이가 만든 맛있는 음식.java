import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long answer = Integer.MAX_VALUE;
    static int N, arr[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        binaryCounting();
        System.out.println(answer);
    }

    private static void binaryCounting() {
        int totalBit = 1<<N;
        long a; // 신맛
        long b; // 쓴맛
        for (int flag = 1; flag < totalBit; flag++) {
            a = 1;
            b = 0;
            for (int i=0; i<N; i++) {
                if ( (flag & 1 << i) != 0) {
                    a *= arr[i][0];
                    b += arr[i][1];
                }
            }
            answer = Math.min(answer, Math.abs(a-b));
        }
    }
}
