import java.io.*;

public class Main {

    static int[][] board;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = word.charAt(j) - '0';
            }
        }
        divisionBoard(0, 0, N);
        System.out.println(sb);
    }

    private static void divisionBoard(int x, int y, int n) {
        if (isAllSameAsZeroOrOne(x, y, n)) {
            sb.append(board[x][y]);
            return;
        }
        int nextN = n / 2;
        sb.append("(");
        divisionBoard(x, y, nextN);
        divisionBoard(x, y + nextN, nextN);
        divisionBoard(x + nextN, y, nextN);
        divisionBoard(x + nextN, y + nextN, nextN);
        sb.append(")");
    }

    private static boolean isAllSameAsZeroOrOne(int x, int y, int n) {
        int tmp = board[x][y];

        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (board[i][j] != tmp) return false;
            }
        }
        return true;
    }
}