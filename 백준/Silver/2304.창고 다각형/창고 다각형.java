import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Point[] points;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        points = new Point[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            points[i] = new Point(L,H);
        }
        Arrays.sort(points);
        int total = 0;
        int tmp = 0;
        Point x = points[0];
        for (int i=1; i<N; i++) {
            if (x.h <= points[i].h) {
                total += (points[i].l - x.l) * x.h;
                x = points[i];
                tmp = i;
            }
        }

        x = points[N-1];
        for (int i = 0; i < N - tmp; i++) {
            if (x.h <= points[N-i-1].h) {
                total += (x.l - points[N-i-1].l) * x.h;
                x = points[N-i-1];
            }
        }
        total += x.h;
        System.out.println(total);
    }
}

class Point implements Comparable<Point> {
    int l,h;

    public Point(int l, int h) {
        this.l = l;
        this.h = h;
    }

    @Override
    public int compareTo(Point o) {
        return this.l - o.l;
    }
}
