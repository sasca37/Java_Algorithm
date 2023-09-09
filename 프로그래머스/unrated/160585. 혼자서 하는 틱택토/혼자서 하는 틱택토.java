import java.util.*;
class Solution {
    public int solution(String[] board) {
        int answer = 0;
        
        char[][] newBoard = new char[board.length][board[0].length()];
        
        int oCnt = 0;
        int xCnt = 0;
        for (int i=0; i < board.length; i++) {
            for (int j =0; j < board[0].length(); j++) {
                char data = board[i].charAt(j);
                if (data == 'O') oCnt++;
                else if (data == 'X') xCnt++;
                newBoard[i][j] = data;
            }
        }
        
        
        int oWin = isWin('O', newBoard);
        int xWin = isWin('X', newBoard);
        System.out.println(oWin +" # " + xWin);
        
        // 둘 다 승리한 경우 
        if (oWin > 0 && xWin > 0) {
            return answer;
        }
        
        // O가 이겼는데 >> O가 X의 갯수보다 1개가 더 많지 않은 경우
        if (oWin > 0 && oCnt - xCnt != 1) {
            return answer;
        }
        
        // X가 이겼는데 >> O와 X의 갯수가 다른 경우
        if (xWin > 0 && oCnt != xCnt) {
            return answer;
        }
        
        // 아무도 승리하지 않았으나 갯수가 다른 경우 
        if (oWin == 0 && xWin == 0) {
            if (oCnt - xCnt > 1 || oCnt < xCnt) {
                return answer;
            }
        }
        
 
        
        answer = 1;        
        return answer;
    }
    
    public int isWin(char c, char[][] board) {
        // 총 9개 
        
        int[][][] points = {
            {{0,0}, {1,1}, {2,2}},
            {{0,2}, {1,1}, {2,0}},
            {{0,0}, {0,1}, {0,2}}, 
            {{0,0}, {1,0}, {2,0}}, 
            {{2,0}, {2,1}, {2,2}}, 
            {{0,2}, {1,2}, {2,2}},
            {{0,1}, {1,1}, {2,1}},
            {{1,0}, {1,1}, {1,2}},
        };
        int cnt = 0;
        for (int i=0; i< points.length; i++) {
            boolean flag = true;
            for (int j =0; j <points[i].length; j++) {
                if (board[points[i][j][0]][points[i][j][1]] != c) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) cnt++;
        }
        return cnt;
    }
}