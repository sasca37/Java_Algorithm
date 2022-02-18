import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int board[][], N,M;
    static int[][] dummy;
    static ArrayList<CCTV> cctvs = new ArrayList<>();
//    // 상 우 하 좌
//    static int[] dx = {-1, 0, 1, 0};
//    static int[] dy = {0, 1, 0, -1};
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
                if (1<= tmp && tmp <= 5) cctvs.add(new CCTV(i,j,tmp));
            }
        }
        cDirections = new int[cctvs.size()];
        backtracking(0);
        System.out.println(answer);
    }
    private static void backtracking(int level) {
        if (level == cctvs.size()) {
            dummy = new int[N][M];
            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    dummy[i][j] = board[i][j];
                }
            }
            for (int i=0; i< cctvs.size(); i++) {
                command(cDirections[i], cctvs.get(i).num, cctvs.get(i).x, cctvs.get(i).y);
            }
            answer = Math.min(answer, getZero());
            return;
        }
        for (int i=0; i<4; i++) {
            cDirections[level] = i;
            backtracking(level+1);
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
            if (d == 0) {
                chkDirections[0] = true;
                chkDirections[1] = true;
            }
            else if (d == 1) {
                chkDirections[1] = true;
                chkDirections[2] = true;
            }
            else if (d==2) {
                chkDirections[2] = true;
                chkDirections[3] = true;
            }
            else {
                chkDirections[0] = true;
                chkDirections[3] = true;
            }
        }
        else if (cNum == 4) {
            if (d == 0) {
                chkDirections[3] = true;
                chkDirections[0] = true;
                chkDirections[1] = true;
            }
            else if (d == 1) {
                chkDirections[0] = true;
                chkDirections[1] = true;
                chkDirections[2] = true;
            }
            else if (d == 2) {
                chkDirections[3] = true;
                chkDirections[2] = true;
                chkDirections[1] = true;
            }
            else {
                chkDirections[0] = true;
                chkDirections[3] = true;
                chkDirections[2] = true;
            }
        }
        else {
            chkDirections[0] = true;
            chkDirections[1] = true;
            chkDirections[2] = true;
            chkDirections[3] = true;
        }
        changeBoard(chkDirections,x,y);
    }

    private static void changeBoard(boolean[] chk, int x, int y) {
        for (int t=0; t<4; t++) {
            if(!chk[t]) continue;
            if (t == 0) {
                for (int i=x; i>=0; i--) {
                    if(dummy[i][y] == 6) break;
                    else if (dummy[i][y] != 0) continue;
                    else dummy[i][y] = -1;
                }
            }
            else if (t == 1) {
                for (int i=y; i<=M-1; i++) {
                    if (dummy[x][i] == 6) break;
                    else if (dummy[x][i] != 0) continue;
                    else dummy[x][i] = -1;
                }
            }
            else if (t == 2) {
                for (int i=x; i<=N-1; i++) {
                    if(dummy[i][y] == 6) break;
                    else if (dummy[i][y] != 0) continue;
                    else dummy[i][y] = -1;
                }
            }
            else {
                for (int i=y; i>=0; i--) {
                    if (dummy[x][i] == 6) break;
                    else if (dummy[x][i] != 0) continue;
                    else dummy[x][i] = -1;
                }
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