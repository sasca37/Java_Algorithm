import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] A = new int[N];
            int[] B = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) B[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(B);
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                int cur = A[i];
                int left = 0;
                int right = M-1;
                int idx = -1;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (B[mid] < cur) {
                        idx = mid;
                        left = mid + 1;
                    } else right = mid - 1;
                }
                cnt += idx + 1;
            }

            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}