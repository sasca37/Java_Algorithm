import java.util.*;
class Solution {
    public int[] solution(String[] maps) {
        int[] answer = {};
        
        char[][] newBoard = new char[maps.length][maps[0].length()];
        
        for (int i=0; i < maps.length; i++) {
            for (int j =0; j < maps[0].length(); j ++) {
                newBoard[i][j] = maps[i].charAt(j);
            }
        }
        List<Integer> arr = new ArrayList<>();
        
        boolean[][] visited = new boolean[newBoard.length][newBoard[0].length];
        
        for (int i=0; i < newBoard.length; i++) {
            for (int j =0; j < newBoard[0].length; j++) {
                if (newBoard[i][j] != 'X' && !visited[i][j]) {
                    visited[i][j] = true;
                    bfs(i, j, visited, newBoard, arr);
                }
            }
        }
        
        if (arr.size() == 0) {
            return new int[]{-1};
        }
        
        Collections.sort(arr);
        
        answer = new int[arr.size()];
        for (int i=0; i<arr.size(); i++) {
            answer[i] = arr.get(i);
        }
        return answer;
    }
    
    public static int[] dx = {-1, 1, 0 , 0};
    public static int[] dy = {0, 0, -1 , 1};
    
    public boolean isValid(int x, int y, char[][] board) {
        if (x < 0 || y < 0 || x > board.length - 1 || y > board[0].length - 1 || board[x][y] == 'X') return false;
        return true;
    }
    
    public void bfs(int x, int y, boolean[][] visited, char[][] board, List<Integer> arr) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{x,y});
        int total = board[x][y] - '0';
        
        while(!que.isEmpty()) {
            int[] points = que.poll();
            int curX = points[0];
            int curY = points[1];
            for (int i =0; i < 4; i ++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];
                
                if (isValid(nx, ny, board) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    total += (board[nx][ny] - '0');
                    que.add(new int[]{nx, ny});
                }
            }
        }
        arr.add(total);
    }
}