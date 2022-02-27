import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M, board[][], subBoard[][];
    static Queue<Zero> queue;
    static ArrayList<Zero> zeros = new ArrayList<>();
    static ArrayList<Combi> combis = new ArrayList<>();
    static boolean[][] visited;
    static int ans = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 0) zeros.add(new Zero(i,j));
                board[i][j] = tmp;
            }
        }
        makeCombination();
        for (int k=0; k<combis.size(); k++) {
            subBoard = new int[N][M];
            visited = new boolean[N][M];
            queue = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    subBoard[i][j] = board[i][j];
                    if (subBoard[i][j] == 2) queue.offer(new Zero(i,j));
                }
            }
            int x = combis.get(k).x;
            int y = combis.get(k).y;
            int z = combis.get(k).z;
            subBoard[zeros.get(x).x][zeros.get(x).y] = 1;
            subBoard[zeros.get(y).x][zeros.get(y).y] = 1;
            subBoard[zeros.get(z).x][zeros.get(z).y] = 1;

            ans = Math.max(ans, bfs());
        }
        System.out.println(ans);
    }

    private static int bfs() {
        int num = 0;
        while (!queue.isEmpty()) {
            Zero tmp = queue.poll();
            visited[tmp.x][tmp.y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx < 0 || ny < 0 || nx > N-1 || ny > M-1 || subBoard[nx][ny] == 1 || visited[nx][ny]) continue;
                subBoard[nx][ny] = 2;
                visited[nx][ny] = true;
                queue.offer(new Zero(nx,ny));
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (subBoard[i][j] == 0) num++;
            }
        }
//        for (int[] u : subBoard) {
//            System.out.println(Arrays.toString(u));
//        }
//        System.out.println();
        return num;
    }

    private static void makeCombination() {
        boolean[] visited = new boolean[zeros.size()];
        int[] numbers = new int[3];
        combination(0,0, numbers, visited);
    }

    private static void combination(int start, int level, int[] numbers, boolean[] visited) {
        if (level == 3) {
            combis.add(new Combi(numbers[0], numbers[1], numbers[2]));
            return;
        }
        for (int i = start; i < visited.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                numbers[level] = i;
                combination(i+1, level+1, numbers, visited);
                visited[i] = false;
            }
        }
    }
}


class Zero {
    int x, y ;
    public Zero(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Combi {
    int x, y, z;

    public Combi(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}