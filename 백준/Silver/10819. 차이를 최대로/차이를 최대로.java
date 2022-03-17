import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, arr[];
    static boolean visited[];
    static int max = 0;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        dfs(0);
        System.out.println(max);
    }

    private static void dfs(int level) {
        if (level == N) {
            int num = 0;
            for (int i=0; i<N-1; i++) {
                num += Math.abs(arr[i]-arr[i+1]);
            }
            max = Math.max(max, num);
            return;
        }

        for (int i=0; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                swap(level, i);
                dfs(level+1);
                swap(i,level);
                visited[i] = false;
            }
        }
    }

    private static void swap(int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;

        boolean k = visited[a];
        visited[a] = visited[b];
        visited[b] = k;
        return;
    }
}
