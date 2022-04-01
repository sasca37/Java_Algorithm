import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N,M, board[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i=0; i<N; i++) {
            String word = br.readLine();
            for (int j=0; j<M; j++) {
                board[i][j] = word.charAt(j)-'0';
            }
        }

        int ans = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k=1; i+k <N; k++) {
                    if (isValid(i+k, j+k)) {
                        int num = chk(i,j,k);
                        ans = Math.max(ans,num);
                    }
                }
            }
        }
        System.out.println(ans);
    }

    private static int chk(int x, int y, int k) {
        int tmp = board[x][y];
        if (tmp == board[x+k][y+k] && tmp == board[x][y+k] && tmp == board[x+k][y]) {
            return (int)Math.pow(k+1, 2);
        }
        return 0;
    }

    private static boolean isValid(int x, int y) {
        if (x < 0 || y < 0 || y > M - 1) return false;
        return true;
    }
}
