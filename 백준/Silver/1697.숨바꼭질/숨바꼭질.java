import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, ans;
    static boolean[] visited = new boolean[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bfs(N, 0);
        System.out.println(ans);
    }

    private static void bfs(int n, int time) {
        Queue<Point> queue = new LinkedList<>();
        boolean[] visited = new boolean[100_001];
        queue.offer(new Point(n, time));
        visited[n] = true;
        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            if (tmp.n == K) {
                ans = tmp.time;
                return;
            }
            if (tmp.n * 2 <= 100_000 && !visited[tmp.n *2]) {
                visited[tmp.n *2] = true;
                queue.offer(new Point(tmp.n * 2, tmp.time + 1));
            }
            if (tmp.n +1 <= 100_000 && !visited[tmp.n+1]) {
                visited[tmp.n+1] = true;
                queue.offer(new Point(tmp.n+1, tmp.time+1));
            }
            if (tmp.n - 1 >= 0 && !visited[tmp.n - 1]) {
                visited[tmp.n-1] = true;
                queue.offer(new Point(tmp.n-1, tmp.time+1));
            }
        }
    }
}
class Point {
    int n, time;

    public Point(int n, int time) {
        this.n = n;
        this.time = time;
    }
}