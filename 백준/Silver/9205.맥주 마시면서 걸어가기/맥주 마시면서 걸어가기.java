import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Point> points;
    static ArrayList<ArrayList<Integer>> graph;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            points = new ArrayList<>();
            graph = new ArrayList<>();
            N = Integer.parseInt(br.readLine());
            // 상근이네집(0), 편의점(N) , 페스티벌(N+1)
            for (int i = 0; i <= N + 1; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points.add(new Point(x, y));
            }
            for (int i = 0; i <= N + 1; i++) graph.add(new ArrayList<>());
            for (int i = 0; i <= N; i++) {
                for (int j = i + 1; j <= N + 1; j++) {
                    if (getDistance(points.get(i), points.get(j))) {
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }
                }
            }
            sb.append(bfs()).append("\n");
        }
        System.out.println(sb);
    }

    private static String bfs(){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+2];
        queue.offer(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            if (tmp == N+1) return "happy";
            for (int x : graph.get(tmp)) {
                if(!visited[x]) {
                    visited[x] = true;
                    queue.offer(x);
                }
            }
        }
        return "sad";
    }

    private static boolean getDistance(Point a, Point b) {
        int num = Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
        if (num <= 1000) return true;
        return false;
    }
}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}