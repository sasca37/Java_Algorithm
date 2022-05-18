import java.util.*;

class Solution {
      public ArrayList<Integer> solution(int n) {
        int[][] board = new int[n*3][n*3];
        int dx[] = {1, 0, -1};
        int dy[] = {-1, 2, -1};
        int start = 1;
        int idx = 0;
        int x = 0;
        int y = n;
        while (n > 0) {
            idx %= 3;
            for (int j = 0; j < n; j++) {
                if (start > 1) {
                    x += dx[idx];
                    y += dy[idx];
                }
                board[x][y] = start++;

            }
            idx++;
            n--;
        }
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 0) answer.add(board[i][j]);
            }
        }
        return answer;
    }
}