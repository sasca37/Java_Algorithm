import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[][] board;
    static int[] numberList = new int[3];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        divisionBoard(0, 0, N);
        System.out.println(numberList[2]);
        System.out.println(numberList[0]);
        System.out.println(numberList[1]);
    }

    private static void divisionBoard(int x, int y, int n) {
        if (isAllSameAsZeroOrOne(x, y, n)) {
            if (board[x][y] == -1) {
                numberList[2]++;
            } else {
                numberList[board[x][y]]++;
            }
            return;
        }
        int tmp = n / 3;
        divisionBoard(x, y, n / 3);
        divisionBoard(x, y + tmp, n / 3);
        divisionBoard(x, y + 2 * tmp, n / 3);
        divisionBoard(x + tmp, y, n / 3);
        divisionBoard(x + tmp, y + tmp, n / 3);
        divisionBoard(x + tmp, y + tmp * 2, n / 3);
        divisionBoard(x + tmp * 2, y, n / 3);
        divisionBoard(x + tmp * 2, y + tmp, n / 3);
        divisionBoard(x + tmp * 2, y + tmp * 2, n / 3);
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