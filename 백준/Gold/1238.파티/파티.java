import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Point>> points = new ArrayList<>();
        for (int i = 0; i <= N; i++) points.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            points.get(from).add(new Point(to,weight));
        }
        int[] distance = null;
        int[] ans = new int[N+1];
        for (int i=1; i<=N; i++) {
            int current = i;
            distance = new int[N+1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            boolean[] visited = new boolean[N+1];
            PriorityQueue<Point> pq = new PriorityQueue<>();
            distance[current] = 0;
            pq.offer(new Point(current, distance[current]));
            while (!pq.isEmpty()) {
                Point tmp = pq.poll();
                if(visited[tmp.to]) continue;
                visited[tmp.to] = true;
                for (int j = 0; j < points.get(tmp.to).size(); j++) {
                    int k = points.get(tmp.to).get(j).to;
                    if(!visited[k] && distance[k] > distance[tmp.to] + points.get(tmp.to).get(j).weight) {
                        distance[k] = distance[tmp.to] + points.get(tmp.to).get(j).weight;
                        pq.offer(new Point(k, distance[k]));
                    }
                }
            }
            if (i != start) ans[i] += distance[start];
            else {
                for (int j=1; j<=N; j++) {
                    ans[j] += distance[j];
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i=1; i<=N; i++) max = Math.max(max, ans[i]);
        System.out.println(max);
    }
}

class Point implements Comparable<Point>{
    int to, weight;

    public Point(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Point o) {
        return this.weight - o.weight;
    }
}
