import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N, board[][], ans;
    static PriorityQueue<Point> points;
    static ArrayList<Integer> keys;
    static LinkedHashMap<Integer, HashSet<Integer>> hashMap = new LinkedHashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i=0; i<N*N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            HashSet<Integer> numbers = new HashSet<>();
            for (int j=0; j<4; j++) {
                numbers.add(Integer.parseInt(st.nextToken()));
            }
            hashMap.put(num, numbers);
        }
        keys = new ArrayList<>(hashMap.keySet());
        for (int t = 0; t < keys.size(); t++) {
            int tmp = keys.get(t);
            if (t == 0) {
                board[1][1] = tmp;
                continue;
            }
            points = new PriorityQueue<>();
            int zeroCnt;
            int pCnt;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] != 0) continue;
                    zeroCnt = 0;
                    pCnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (isValid(nx,ny)) {
                            if (board[nx][ny] == 0) zeroCnt++;
                            else if (hashMap.get(tmp).contains(board[nx][ny])) pCnt++;
                        }
                    }
                    points.offer(new Point(i,j,pCnt, zeroCnt));
                }
            }
            Point point = points.poll();
            board[point.x][point.y] = tmp;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int pCnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (isValid(nx,ny) && hashMap.get(board[i][j]).contains(board[nx][ny])) pCnt++;
                }
                if (pCnt == 0) continue;
                else if (pCnt == 1) ans += 1;
                else ans += Math.pow(10, pCnt-1);
            }
        }
        System.out.println(ans);
    }

    private static boolean isValid(int x, int y) {
        if (x >= 0 && y >= 0 && x < N && y < N ) return true;
        return false;
    }
}

class Point implements Comparable<Point> {
    // x, y : 좌표, p : 친구 갯수, e : 빈 자리 갯수
    int x, y, p, e;

    public Point(int x, int y, int p, int e) {
        this.x = x;
        this.y = y;
        this.p = p;
        this.e = e;
    }

    @Override
    public int compareTo(Point o) {
        // 인기 순위가 같다면
        if (this.p == o.p) {
            // 빈 칸이 같다면
            if (this.e == o.e) {
                // 행이 같다면
                if (this.x == o.x) {
                    return this.y - o.y;
                }
                return this.x-o.x;
            }
            return (this.e - o.e) * -1;
        }
        return (this.p - o.p) * -1;
    }
}