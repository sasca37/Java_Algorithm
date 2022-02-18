import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int board[][], N,M;
    static boolean[][] visited;
    static int[][] dummy;
    static ArrayList<CCTV> cctvs = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int answer = Integer.MAX_VALUE;
    static int[] cDirections;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                board[i][j] = tmp;
                if (tmp!=0 && tmp != 6) cctvs.add(new CCTV(i,j,tmp));
            }
        }
        cDirections = new int[cctvs.size()];
        permutation(0);
        System.out.println(answer);
    }
    private static void permutation(int level) {
        if (level == cctvs.size()) {
            dummy = new int[N][M];
            visited = new boolean[N][M];
            for (int i=0; i<N; i++) {
                dummy[i] = Arrays.copyOf(board[i],M);
            }
            for (int i=0; i< cctvs.size(); i++) {
                command(cDirections[i], cctvs.get(i).num, cctvs.get(i).x, cctvs.get(i).y);
            }
            answer = Math.min(answer, getZero());
            return;
        }
        for (int i=0; i<4; i++) {
            cDirections[level] = i;
            permutation(level+1);
        }
    }

    private static void command(int d, int cNum, int x, int y) {
        boolean[] chkDirections = new boolean[4];
        if (cNum == 1) {
            chkDirections[d] = true;
        }
        else if (cNum == 2) {
            if(d == 0 || d == 2) {
                chkDirections[0] = true;
                chkDirections[2] = true;
            }
            else {
                chkDirections[1] = true;
                chkDirections[3] = true;
            }
        }
        else if (cNum == 3) {
            chkDirections[d % 4] = true;
            chkDirections[(d+1) % 4] = true;
        }
        else if (cNum == 4) {
            chkDirections[(3+d)%4] = true;
            chkDirections[(d)%4] = true;
            chkDirections[(1+d)%4] = true;
        }
        else Arrays.fill(chkDirections, true);
        changeBoard(chkDirections,x,y);
    }

    private static void changeBoard(boolean[] chk, int x, int y) {
        for (int t=0; t<4; t++) {
            if(!chk[t]) continue;
            int lx = x, ly = y;
            while(true) {
                int nx = lx +dx[t];
                int ny = ly +dy[t];
                if (nx < 0 || ny < 0 || nx > N-1 || ny > M-1 || dummy[nx][ny] == 6 ) break;
                dummy[nx][ny] = -1;
                lx = nx;
                ly = ny;
            }
        }
    }

    public static int getZero() {
        int num = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dummy[i][j] == 0) num++;
            }
        }
        return num;
    }
}

class CCTV {
    int x, y, num;

    public CCTV(int x, int y, int num) {
        this.x = x;
        this.y = y;
        this.num = num;
    }
}