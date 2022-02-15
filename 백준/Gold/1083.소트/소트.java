import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,S, arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(br.readLine());
        int idx=0;
        while (true) {
            if(idx == N-1) break;
            int tmp = S;
            int max = getMaxIndex(idx,tmp);
//            System.out.println(idx +" " + S +" " + max +" " + Arrays.toString(arr));
            // 가능한 거리 중 다른 인덱스라면 거리만큼 빼준다.
            if(max != idx) {
                S -=  max-idx;
                swap(idx, max);
                if(S == 0) break;
            }
//            System.out.println(Arrays.toString(arr));
            idx++;
        }
        for (int x : arr) sb.append(x).append(" ");

        System.out.println(sb);
    }

    private static int getMaxIndex(int idx, int s) {
        int max = arr[idx];
        if(idx+s < N) {
            for (int i=idx+1; i<=idx+s; i++) {
                max = Math.max(max, arr[i]);
            }
            for (int i=idx+1; i<=idx+s; i++) {
                if(arr[i] == max) return i;
            }
        }
        else {
            for (int i = idx+1; i< N; i++) {
                max = Math.max(max, arr[i]);
            }
            for (int i = idx+1; i< N; i++) {
                if(arr[i] == max) return i;
            }
        }
        return idx;
    }

    private static void swap(int idx, int max) {
        int tmp = arr[max];
        for (int i=max; i>idx; i--) {
            arr[i] = arr[i-1];
        }
        arr[idx] = tmp;
    }
}