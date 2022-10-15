import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static Construction[] constructions;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new long[N + 1][M + 1];

        K = Integer.parseInt(br.readLine());
        constructions = new Construction[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            constructions[i] = new Construction(x1, y1, x2, y2);
        }

        dp[0][0] = 1;
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < M + 1; j++) {
                if (i == 0 && j == 0) continue;
                dp[i][j] = getValue(i,j);
            }
        }

        System.out.println(dp[N][M]);
    }


    private static long getValue(int x, int y) {
        return isValid(x - 1, y, x, y) + isValid(x, y - 1, x, y);
    }

    private static long isValid(int beforeX, int beforeY, int x, int y) {
        if (beforeX < 0 || beforeY < 0 || beforeX > N || beforeY > M) return 0;
        for (int i = 0; i < K; i++) {
            int x1 = constructions[i].x1;
            int y1 = constructions[i].y1;
            int x2 = constructions[i].x2;
            int y2 = constructions[i].y2;
            if ( (x1 == beforeX && y1 == beforeY && x2 == x && y2 == y) ||
                (x2 == beforeX && y2 == beforeY && x1 == x && y1 == y)
            ) return 0;
        }
        return dp[beforeX][beforeY];
    }

    static class Construction {
        private int x1;
        private int y1;
        private int x2;
        private int y2;

        public Construction(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}
