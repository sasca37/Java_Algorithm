import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int F, G,U,D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        int ans = bfs(S,0);
        if(ans != -1) System.out.println(ans);
        else System.out.println("use the stairs");
    }

    private static int bfs(int s, int time) {
        Queue<Point> queue = new LinkedList<>();
        boolean[] visited = new boolean[F+1];
        visited[s] = true;
        queue.offer(new Point(s, time));
        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            if (tmp.x == G) return tmp.time;
            if (tmp.x + U <= F && !visited[tmp.x+U]) {
                visited[tmp.x+U] = true;
                queue.offer(new Point(tmp.x+U, tmp.time+1));
            }
            if (tmp.x - D > 0 && !visited[tmp.x-D]) {
                visited[tmp.x-D] = true;
                queue.offer(new Point (tmp.x-D, tmp.time+1));
            }
        }
        return -1;
    }
}

class Point {
    int x, time;

    public Point(int x, int time) {
        this.x = x;
        this.time = time;
    }
}