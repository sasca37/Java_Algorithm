import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        Point[] times = new Point[N];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int finish = Integer.parseInt(st.nextToken());
            times[i] = new Point(start, finish);
        }

        Arrays.sort(times);
        pq.offer(times[0].finish);
        for (int i = 1; i < N; i++) {
            if (pq.peek() <= times[i].start) pq.poll();
            pq.offer(times[i].finish);
        }

        System.out.println(pq.size());
    }

    static class Point implements Comparable<Point> {
        int start, finish;

        public Point(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }

        @Override
        public int compareTo(Point o) {
            if (this.start == o.start) return this.finish - o.finish;
            return this.start - o.start;
        }
    }
}