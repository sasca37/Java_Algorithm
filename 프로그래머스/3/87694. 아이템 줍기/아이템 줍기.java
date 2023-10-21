import java.util.*;
class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        
        int[][] board = new int[102][102];
        
        for (int i=0; i < rectangle.length; i++) {
            int[] curArr = rectangle[i];
            int x1 = curArr[0] * 2;
            int y1 = curArr[1] * 2;
            int x2 = curArr[2] * 2;
            int y2 = curArr[3] * 2;
            
            for (int j = y1; j <= y2; j++) {
                for (int k=x1; k <= x2; k++) {
                    board[k][j] = 1;
                }
            }
        }
        
        // System.out.println(Arrays.deepToString(board));
        
        
        boolean[][] visited = new boolean[102][102];
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;
        
        Queue<Point> que = new LinkedList<>();        
        que.add(new Point(characterX, characterY, 0));
        visited[characterX][characterY] = true; 
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            // System.out.println("X : " + p.x + ", Y : " + p.y + " CNT : " + p.cnt);
            if (p.x == itemX && p.y == itemY) {
                answer = p.cnt;
                break;
            }
            for (int i=0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                
                if (board[nx][ny] != 0 && canNext(nx, ny, board) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    que.add(new Point(nx, ny, p.cnt + 1));
                }
            }
            
        }
        
        
        return answer / 2;
    }
    
    public boolean canNext(int x, int y, int[][] board) {
        int isZero = 0;
        for (int i=0; i< 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && board[nx][ny] == 0) {
                isZero++;
            }
        }
        
        return isZero > 0;
    }
    
    public static int[] dx = {0, 0, 1, -1, -1, -1, 1, 1};
    public static int[] dy = {-1, 1, 0, 0, -1, 1, -1, 1};
    
    static class Point {
        int x;
        int y;
        int cnt;
        
        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}