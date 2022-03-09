import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] numbers;
    static boolean[] visited;
    static Point[] arr;
    static double ans;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            ans = Integer.MAX_VALUE;
            arr = new Point[N];
            numbers = new int[N];
            visited = new boolean[N];
            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            combination(0,0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void combination(int start, int level) {
        if (level == N/2 ) {
            ans = Math.min(ans, getVector());
            return;
        }
        for (int i = start; i < N; i++) {
            visited[i] = true;
            combination(i + 1, level + 1);
            visited[i] = false;
        }
    }

    private static double getVector() {
        double ansX  = 0;
        double ansY  = 0;
        for (int i=0; i<N; i++) {
            if(visited[i]) {
                ansX += arr[i].x;
                ansY += arr[i].y;
            }
            else {
                ansX -= arr[i].x;
                ansY -= arr[i].y;
            }
        }
        return Math.sqrt(Math.pow(ansX,2) + Math.pow(ansY,2));
    }

}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
