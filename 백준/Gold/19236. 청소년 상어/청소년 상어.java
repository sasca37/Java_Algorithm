import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] directions = {
            {}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}
    };
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int[][] board = new int[4][4];
        List<Fish> fishes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                fishes.add(new Fish(num, dir, true));
                board[i][j] = num;
            }
        }

        Collections.sort(fishes);


        dfs(0, 0, 0, 0, board, fishes);
        System.out.println(ans);

    }

    private static void dfs(int sharkX, int sharkY, int sharkDir, int total, int[][] board, List<Fish> fishes) {


        // 상어가 물고기를 잡아먹는다.
        int fishNum = board[sharkX][sharkY];
        board[sharkX][sharkY] = -1;
        fishes.get(fishNum - 1).isAlive = false;
        sharkDir = fishes.get(fishNum -1).dir;
        total += fishes.get(fishNum - 1).num;

        // 물고기 순차적 이동
        moveFish(fishes, board);

        List<int[]> list = getList(sharkX, sharkY, sharkDir, board);
        if (list.size() == 0) {
            ans = Math.max(ans, total);
            return;
        }
        for (int[] l : list) {
            int[][] newBoard = getBoard(board);
            List<Fish> newFishes = getFishes(fishes);
            newBoard[sharkX][sharkY] = 0;
            dfs(l[0], l[1], sharkDir, total, newBoard, newFishes);
        }
    }

    private static List<int[]> getList(int sharkX, int sharkY, int sharkDir, int[][] board) {
        List<int[]> list = new ArrayList<>();
        int nx = sharkX + directions[sharkDir][0];
        int ny = sharkY + directions[sharkDir][1];
        while (isValid(nx, ny)) {
            if (board[nx][ny] > 0) {
                list.add(new int[] {nx, ny});
            }
            nx += directions[sharkDir][0];
            ny += directions[sharkDir][1];
        }
        return list;
    }


    private static void moveFish(List<Fish> fishes, int[][] board) {
        for (int i = 1; i <= fishes.size(); i++) {
            int[] point = getPoint(i, board);
            if (point == null) continue;
            int x = point[0];
            int y = point[1];
            int dir = fishes.get(i - 1).dir;
            for (int d = dir; d < dir + 8; d++) {
                d = (d > 8) ? d - 8 : d;
                int nx = x + directions[d][0];
                int ny = y + directions[d][1];
                if (!isValid(nx, ny) || board[nx][ny] == -1) continue;
                // 비어있는 공간이라면 물고기를 이동
                if (board[nx][ny] == 0) {
                    fishes.get(i - 1).dir = d;
                    board[nx][ny] = board[x][y];
                    board[x][y] = 0;
                }
                // 물고기가 있는 곳으로 이동한다면
                else {
                    int temp = board[nx][ny];
                    fishes.get(i - 1).dir = d;
                    board[nx][ny] = board[x][y];
                    board[x][y] = temp;
                }
                break;
            }
        }
    }

    private static boolean isValid(int x, int y) {
        if (x < 0 || y < 0 || x > 3 || y > 3) return false;
        return true;
    }

    private static int[] getPoint(int idx, int[][] board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == idx) return new int[]{i, j};
            }
        }
        return null;
    }


    private static List<Fish> getFishes(List<Fish> fishes) {
        List<Fish> newFishes = new ArrayList<>();
        for (Fish fish : fishes) {
            newFishes.add(new Fish(fish.num, fish.dir, fish.isAlive));
        }
        return newFishes;
    }

    private static int[][] getBoard(int[][] board) {
        int[][] newBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    private static void printBoard(int[][] board, List<Fish> fishes) {
        for (int[] b : board) {
            for (int x : b) {
                if (x == -1) System.out.print("상어 ");
                else if (x == 0) System.out.print("X ");
                else System.out.print(x +""+ fishes.get(x-1).dir+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static class Fish implements Comparable<Fish> {
        private int num;
        private int dir;
        private boolean isAlive;

        public Fish(int num, int dir, boolean isAlive) {
            this.num = num;
            this.dir = dir;
            this.isAlive = isAlive;
        }

        public int compareTo(Fish o) {
            return this.num - o.num;
        }
    }

}
