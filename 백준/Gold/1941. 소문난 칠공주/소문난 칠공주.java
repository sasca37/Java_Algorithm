import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] board;
    static int[] input, numbers, testBoard[];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new char[5][5];
        for (int i = 0; i < 5; i++) {
            String word = br.readLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = word.charAt(j);
            }
        }

        input = new int[25];
        numbers = new int[7];
        for (int i=0; i< input.length; i++) {
            input[i] = i;
        }
        combination(0,0);
        System.out.println(answer);
    }

    private static void combination(int cnt, int start) {
        if(cnt == 7) {
            if(makeWordAndChk()) {
                int x = numbers[0] / 5;
                int y = numbers[0] % 5;
                bfs(x,y);
            }
            return;
        }
        for (int i = start; i < 25; i++) {
            numbers[cnt] = input[i];
            combination(cnt+1, i+1);
        }
    }

    private static void bfs(int x, int y) {
        int cnt = 1;
        boolean[][] visited = new boolean[5][5];
        visited[x][y] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x,y));
        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx < 0 || ny < 0 || nx > 4 || ny > 4 || visited[nx][ny] || testBoard[nx][ny] == 0) continue;
                cnt ++;
                visited[nx][ny] = true;
                queue.add(new Point(nx, ny));
            }
        }
        if (cnt == 7) answer++;
    }

    private static boolean makeWordAndChk() {
        StringBuilder sb = new StringBuilder();
        testBoard = new int[5][5];
        int yCnt = 0;
        for (int i = 0; i < 7; i++) {
            int x = numbers[i] / 5;
            int y = numbers[i] % 5;
            testBoard[x][y] = 1;
            if (board[x][y] == 'Y') yCnt++;
            sb.append(board[x][y]);
        }
        if(yCnt >= 4) return false;
        return true;
    }
}

class Point {
    int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
