import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, arr[];
    static boolean[] visited;
    static int[] operation = new int[4];
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int x;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        visited = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) operation[i] += Integer.parseInt(st.nextToken());

        dfs(0, arr[0]);
        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int level, int total) {
        if(level == N-1) {
            max = Math.max(max, total);
            min = Math.min(min, total);
            return;
        }
        for (int i=0; i<4; i++) {
            if(operation[i] > 0) {
                operation[i]--;
                if(i==0) dfs(level+1, total + arr[level+1]);
                else if (i==1) dfs(level+1, total - arr[level+1]);
                else if (i==2) dfs(level+1, total * arr[level+1]);
                else dfs(level+1, total / arr[level+1]);
                operation[i]++;
            }
        }
    }
}