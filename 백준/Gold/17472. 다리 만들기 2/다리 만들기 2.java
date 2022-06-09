import java.io.*;
import java.util.*;

public class Main{

    static int N, M;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] parents;
    static PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int idx = divideBoard();
        makeSet(idx);
        getEdges();

        int cnt = 0;
        int answer = 0;
        while (!edges.isEmpty()) {
            Edge tmp = edges.poll();
            if (union(tmp.from, tmp.to)) {
                cnt++;
                answer+= tmp.weight;
                if (cnt == idx-1) break;
            }
        }
        if (cnt < idx-1) System.out.println(-1);
        else System.out.println(answer);
    }

    private static void getEdges() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != 0) {
                    int num = board[i][j];
                    int idx = 0;
                    while(idx < 4) {
                        int nx = i + dx[idx];
                        int ny = j + dy[idx];
                        int distance = 0;
                        while(true) {
                            if (!isValid(nx,ny) || board[nx][ny] == num) break;
                            if (board[nx][ny] == 0) {
                                distance++;
                                nx += dx[idx];
                                ny += dy[idx];
                            }
                            else {
                                if(distance < 2) break;
                                edges.add(new Edge(num, board[nx][ny], distance));
                                break;
                            }
                        }
                        idx++;
                    }
                }
            }
        }
    }

    private static void makeSet(int idx) {
        parents = new int[idx + 1];
        for (int i=1; i<=idx; i++) parents[i] = i;
    }

    private static int findSet(int a) {
        if (parents[a] == a) return a;
        return parents[a] = findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    static boolean[][] visited;
    private static int divideBoard() {
        visited = new boolean[N][M];
        int idx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    bfs(i,j, idx);
                    idx++;
                }
            }
        }
        return idx-1;
    }

    private static void bfs(int x, int y, int idx) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            board[tmp[0]][tmp[1]] = idx;
            for (int i = 0; i < 4; i++) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];
                if (isValid(nx,ny) && board[nx][ny] !=0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean isValid(int x, int y) {
        if (x < 0 || y < 0 || x > N-1 || y > M-1) return false;
        return true;
    }

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
