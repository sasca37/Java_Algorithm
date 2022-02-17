import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int board[][], white, blue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        recur(0,0, N);
        System.out.println(white);
        System.out.println(blue);
    }

    public static void recur(int r, int c, int size) {
        int sum = 0;
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                sum += board[i][j];
            }
        }
        if (sum == size * size) blue++;
        else if (sum == 0) white++;
        else { // 색이 섞여 있는 경우
            int half = size / 2;
            recur(r,c,half);
            recur(r, c+half, half);
            recur(r+half, c, half);
            recur(r+half, c+half, half);
        }
    }
}
