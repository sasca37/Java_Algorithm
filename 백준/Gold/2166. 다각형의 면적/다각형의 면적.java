import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Point[] points = new Point[N+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x,y);
        }
        points[N] = new Point(points[0].x, points[0].y);
        long first = 0;
        long second = 0;
        for (int i=0; i<N; i++) {
            first += points[i].x * points[i+1].y;
            second += points[i].y * points[i+1].x;
        }
        double ans = 1.0 * Math.abs(first-second) / 2;
        System.out.printf("%.1f", ans);
    }
}

class Point {
    long x,y;

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}

