import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for (int i = 0; i < 5; i++) {
            char[][] board = new char[5][5];
            for (int j = 0; j < 5; j++) {
                board[j] = places[i][j].toCharArray();
            }
            boolean flag = true;
            for (int x = 0; x < 5 && flag; x++) {
                for (int y = 0; y < 5; y++) {
                    if (board[x][y] == 'P') {
                        if(!bfs(x,y,board)) {
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if (flag) answer[i] = 1;
            else answer[i] = 0;
        }
        return answer;
    }

    private static boolean bfs(int x, int y, char[][] board) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];
                if (nx < 0 || ny <0 || nx > 4 || ny > 4 || (nx == x && ny == y)) continue;
                int d = Math.abs(nx - x) + Math.abs(ny - y);
                if (d <= 2 && board[nx][ny] == 'P') return false;
                if(d == 1 && board[nx][ny] == 'O') {
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        return true;
    }
}