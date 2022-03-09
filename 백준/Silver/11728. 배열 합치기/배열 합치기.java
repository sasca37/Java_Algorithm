import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] a = new int[N];
        int[] b = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) a[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) b[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(a);
        Arrays.sort(b);
        int aPointer = 0;
        int bPointer = 0;
        while (aPointer+bPointer != N+M) {
            if (aPointer == N) {
                for (int i=bPointer; i < M; i++) {
                    sb.append(b[bPointer++]).append(" ");
                    break;
                }
            }
            else if (bPointer == M) {
                for (int i=aPointer; i<N; i++) {
                    sb.append(a[aPointer++]).append(" ");
                    break;
                }
            }
            else if (a[aPointer] < b[bPointer]) {
                sb.append(a[aPointer++]).append(" ");
            }
            else {
                sb.append(b[bPointer++]).append(" ");
            }
        }
        System.out.println(sb);
    }
}
