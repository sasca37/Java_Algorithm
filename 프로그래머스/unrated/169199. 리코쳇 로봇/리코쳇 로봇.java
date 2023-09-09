import java.util.*;
class Solution {
    
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] board) {
        int answer = 0;
        char[][] newBoard = new char[board.length][board[0].length()];
        Robot robot = null;
        
        for (int i=0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == 'R') {
                    robot = new Robot(i, j , 0);
                }
                newBoard[i][j] = board[i].charAt(j);
            }
        }
        answer = bfs(robot, newBoard);
        // System.out.println(Arrays.deepToString(newBoard));
        return answer;
    }
    
    public int bfs(Robot robot, char[][] board) {
        Queue<Robot> que = new LinkedList<>();
        que.add(robot);
        boolean[][] visited = new boolean[board.length][board[0].length];
        visited[robot.getX()][robot.getY()] = true;
        int ans = -1;
        while(!que.isEmpty()) {
            Robot r = que.poll();
            int curX = r.getX();
            int curY = r.getY();
            // 동 서 남 북 이동 - 갈 수 있는 끝까지 (장애물이거나 끝이거나)
            for (int i=0; i< 4; i ++) {
                int[] points = getMaxPoint(curX, curY, i, board);
                // 이동을 할 수 있는 경우
                if (points[0] != curX || points[1] != curY) {
                    if (board[points[0]][points[1]] == 'G') {
                        ans = r.getCnt() + 1;
                        return ans;
                    }
                    if (!visited[points[0]][points[1]]) {
                        visited[points[0]][points[1]] = true;
                        que.add(new Robot(points[0], points[1], r.getCnt() + 1));
                    }
                }
            }
        }

        return ans;
    }
    
    public int[] getMaxPoint(int x, int y, int direction, char[][] board) {
        boolean isValid = true;
        while(true) {
            x += dx[direction];
            y += dy[direction];
            if (x < 0 || y < 0 || x > board.length - 1 || y > board[0].length - 1 || board[x][y] == 'D') {
                x -= dx[direction];
                y -= dy[direction];
                break;
            }
        }
        return new int[] {x, y};
    }
    
    static class Robot {
        private int x;
        private int y;
        private int cnt;
        
        public Robot(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
        
        public int getX() {
            return x;
        }
        
        public int getY() {
            return y;
        }
        
        public int getCnt() {
            return cnt;
        }
        
        public void setX(int x) {
            this.x = x;
        }
        
        public void setY(int y) {
            this.y =y;
        }
        
        public void setCnt(int cnt) {
            this.cnt = cnt;
        }
    }
}