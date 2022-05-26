import java.util.*;
class Solution {
      static int N, M;
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        bfs(maps);
        if (answer == Integer.MAX_VALUE) return -1;
        return answer;
    }

    private static void bfs(int[][] maps) {
        boolean visited[][] = new boolean[N][M];
        Queue<Point> queue = new LinkedList<>();
        visited[0][0] = true;
        queue.add(new Point(0, 0,1));
        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            if (tmp.x == N-1 && tmp.y == M-1) {
                answer = tmp.time;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx < 0 || ny < 0 || nx > N-1 || ny > M-1 || visited[nx][ny] || maps[nx][ny] == 0) continue;
                visited[nx][ny] = true;
                queue.add(new Point(nx, ny, tmp.time + 1));
            }
        }
    }

    static class Point {
        int x,y, time;

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}