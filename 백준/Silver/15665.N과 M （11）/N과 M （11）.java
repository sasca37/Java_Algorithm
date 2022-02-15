import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {
    static int N,M, numbers[], arr[];
    static LinkedHashSet<String> hashSet = new LinkedHashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        numbers = new int[M];
        dfs(0);
        for (String x : hashSet) sb.append(x).append("\n");
        System.out.println(sb);
    }

    private static void dfs(int level) {
        if (level == M) {
            StringBuilder sb = new StringBuilder();
            for (int x : numbers) sb.append(x).append(" ");
            hashSet.add(sb.toString());
            return;
        }
        for (int i=0; i<N; i++) {
                numbers[level] = arr[i];
                dfs(level+1);
        }
    }
}