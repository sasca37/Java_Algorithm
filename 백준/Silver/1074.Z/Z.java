import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2,N);
        recur (r,c,size);
        System.out.println(answer);
    }

    // 2^N = 2^N-1 + 2^N-1
    private static void recur(int x, int y, int size) {
        int currentSize = size * size / 4;
        if (size == 1) return;
        // 1 사분면
        if (x < size / 2 && y < size / 2) {
            recur( x, y, size / 2);
        }
        // 2 사분면
        else if ( x < size / 2 && y >= size / 2) {
            answer += currentSize;
            recur(x, y - size / 2, size / 2);
        }
        // 3 사분면
        else if (x >= size / 2 && y < size / 2) {
            answer += currentSize * 2;
            recur(x - size / 2, y, size / 2);
        }
        else {
            answer += currentSize * 3;
            recur(x - size / 2, y- size / 2, size / 2);
        }
    }
}
