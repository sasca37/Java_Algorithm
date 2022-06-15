import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] a = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) a[i] = Integer.parseInt(st.nextToken());

            int M = Integer.parseInt(br.readLine());
            int[] b = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) b[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(a);
            for (int i=0; i<M; i++) {
                int left = 0;
                int right = N-1;
                boolean flag = false;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (a[mid] == b[i]) {
                        sb.append(1).append("\n");
                        flag = true;
                        break;
                    }
                    else if (a[mid] > b[i]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                if (!flag) sb.append(0).append("\n");
            }
        }
        System.out.println(sb);
    }


}