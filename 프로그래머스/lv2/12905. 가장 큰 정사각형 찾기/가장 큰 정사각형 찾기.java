class Solution
{
    public int solution(int [][]board) {
        int N = board.length;
        int M = board[0].length;
        int maxSize = 0;
        boolean isExistOne = false;
        int[][] arr = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                arr[i][j] = board[i-1][j-1];
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (arr[i][j] == 1) isExistOne = true;
                if (arr[i][j] == 1 && arr[i-1][j] > 0 && arr[i][j-1] > 0 && arr[i-1][j-1] > 0) {
                    arr[i][j] = Math.min(arr[i-1][j], Math.min(arr[i][j-1], arr[i-1][j-1])) + 1;
                    maxSize = Math.max(maxSize, arr[i][j]);
                }
            }
        }

        if (maxSize == 0 && isExistOne) return 1;
        return maxSize * maxSize;
    }
}