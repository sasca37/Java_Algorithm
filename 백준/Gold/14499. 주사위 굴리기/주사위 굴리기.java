import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, X, Y, cnt;
    static int[] commands;
    static int[][] board;
    static int[][] numbers = {
            {0,1}, {0,-1}, {-1,0}, {1,0}
    };
    static int[] dices = {0, 0, 0, 0, 0, 0, 0};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        cnt = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        commands = new int[cnt];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cnt; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < cnt; i++) {
            int[] nums = numbers[commands[i] -1] ;
            if (!isValid(nums)) continue;
            X += nums[0];
            Y += nums[1];
            moveDice(commands[i]);
            if (board[X][Y] == 0) {
                board[X][Y] = dices[6];
            } else {
                dices[6] = board[X][Y];
                board[X][Y] = 0;
            }
            sb.append(dices[1]).append("\n");
        }
        System.out.println(sb);
    }

    private static void moveDice(int num) {
        int tmp = dices[1];
        switch (num) {
            case 1 :
                dices[1] = dices[3];
                dices[3] = dices[6];
                dices[6] = dices[4];
                dices[4] = tmp;
                break;
            case 2 :
                dices[1] = dices[4];
                dices[4] = dices[6];
                dices[6] = dices[3];
                dices[3] = tmp;
                break;
            case 3 :
                dices[1] = dices[5];
                dices[5] = dices[6];
                dices[6] = dices[2];
                dices[2] = tmp;
                break;
            case 4 :
                dices[1] = dices[2];
                dices[2] = dices[6];
                dices[6] = dices[5];
                dices[5] = tmp;
                break;
        }
    }

    private static boolean isValid(int[] numbers) {
        int curX = X + numbers[0];
        int curY = Y + numbers[1];
        if (curX < 0 || curY < 0 || curX > N-1 || curY > M-1) return false;
        return true;
    }

}
