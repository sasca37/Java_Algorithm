import java.util.*;

class Solution {
    
    public static int[] dx = {-1, 1, 0 , 0};
    public static int[] dy = {0, 0, 1, -1};
    public static int leverX;
    public static int leverY;
    
    public int solution(String[] maps) {
        int answer = 0;
        
        // START = 1, EXIT : 2, L : 3, 벽 : -1, 통로 : 0 
        int[][] board = new int[maps.length][maps[0].length()];
        int startX = 0;
        int startY = 0; 
        
        for (int i=0; i< maps.length; i++) {
            for (int j=0; j<maps[i].length(); j++) {
                board[i][j] = convertBoard(maps[i].charAt(j)+"");
                if (board[i][j] == 1) {
                    startX = i;
                    startY = j;
                }
            }
        }
        
        answer += bfs(startX, startY, 0, board, true);
        if (answer == -1) {
            return answer;
        }
        int next = bfs(leverX, leverY, 0, board, false);
        if (next == -1) {
            return -1;
        }
        answer += next;
        
        return answer;
    }
    
    private int bfs(int x, int y, int p, int[][] board, boolean isLever) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        visited[x][y] = true;
        queue.add(new int[]{x,y,p});
        
        while (!queue.isEmpty()) {
            int[] points = queue.poll();
            int curX = points[0];
            int curY = points[1];
            int curP = points[2];
            
            for (int i=0; i<4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];
                int nP = curP + 1;
                if (isValid(nx, ny, board, visited)) {
                    if (board[nx][ny] == 3 && isLever) {
                        leverX = nx;
                        leverY = ny;
                        return nP;
                    }
                    if (board[nx][ny] == 2 && !isLever) {
                        return nP;
                    }
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, nP});
                }
            }
        }
        return -1;
    }
    
    private boolean isValid(int x, int y, int[][] board, boolean[][] visited) {
        if (x < 0 || y < 0 || x > board.length - 1 || y > board[0].length - 1 || visited[x][y] || board[x][y] == -1) {
            return false;
        }
        return true;
    }
    
    private int convertBoard(String map) {
        if (map.equals("S")) {
            return 1;
        } else if (map.equals("E")) {
            return 2;
        } else if (map.equals("L")) {
            return 3;
        } else if (map.equals("X")) {
            return -1;
        }
        return 0;
    }
}