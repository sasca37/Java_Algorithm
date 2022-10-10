import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 0 : 상 , 1: 하, 2: 좌, 3: 우
    static int[][] direction = {
            {-1, 0}, {+1, 0}, {0, -1}, {0, +1}
    };
    static int N, M, K;
    static int[][] board;
    static int[][] kBoard;
    static int[][] chkBoard;
    static Shark[] sList;
    static List<Shark> sPoint = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        kBoard = new int[N][N];
        chkBoard = new int[N][N];
        sList = new Shark[M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num != 0) {
                    sPoint.add(new Shark(num , i, j));
                    board[i][j] = num;
                    chkBoard[i][j] = num;
                    kBoard[i][j] = K;
                } else {
                    board[i][j] = -1;
                    chkBoard[i][j] = -1;
                }
            }
        }

        int[] dList = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            dList[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 0; i < M; i++) {
            int[][] dir = new int[4][4];
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                int[] list = {
                        Integer.parseInt(st.nextToken()) -1,
                        Integer.parseInt(st.nextToken()) -1,
                        Integer.parseInt(st.nextToken()) -1,
                        Integer.parseInt(st.nextToken()) -1
                };
                dir[j] = list;
            }
            sList[i] = new Shark(i, dList[i], dir, true);
        }


        Arrays.sort(sList);
        Collections.sort(sPoint);

        int cnt = 1;
        while (cnt <= 1000) {
            for (int i = 0; i < M; i++) {
                Shark shark = sList[i];
                if(!shark.isAlive) continue;
                int curX = sPoint.get(i).x;
                int curY = sPoint.get(i).y;
                int dir = shark.dir;
                int[] ways = getBestWay(i, shark.dList[dir], board, kBoard, chkBoard);
                for (int d = 0; d < 4; d++) {
                    int nx = curX + direction[ways[d]][0];
                    int ny = curY + direction[ways[d]][1];
                    if (isValid(nx, ny)) {
                        // 냄세가 없는 경우 : kBoard 가 0
                        if (kBoard[nx][ny] == 0) {
                            // 이전에 온 상어가 있으면 밖으로 나간다. (냄세 뿌리기 전으로 가정)
                            if (board[nx][ny] != -1) {
                                shark.isAlive = false;
                                board[curX][curY] = -1;
                                break;
                            }
                            // 빈곳이므로 상어 이동
                            else {
                                board[nx][ny] = shark.num + 1;
                                chkBoard[nx][ny] = shark.num + 1;
                                board[curX][curY] = -1;
                                shark.dir = ways[d];
                                sPoint.get(i).x = nx;
                                sPoint.get(i).y = ny;
                                break;
                            }
                        }
                        // 냄세가 있는 경우
                        else {
                            // 자기 냄세인 경우
                            if (chkBoard[nx][ny] == chkBoard[curX][curY]) {
                                board[nx][ny] = shark.num + 1;
                                chkBoard[nx][ny] = shark.num + 1;
                                board[curX][curY] = -1;
                                shark.dir = ways[d];
                                sPoint.get(i).x = nx;
                                sPoint.get(i).y = ny;
                                break;
                            }
                        }
                    }
                }

            }
            spreadSmell(board, kBoard, chkBoard);

            int n = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(board[i][j] != -1) n++;
                }
            }
            if (n == 1) break;
            cnt++;
        }
        if (cnt == 1001) {
            System.out.println(-1);
        }
        else {
            System.out.println(cnt);
        }
    }

    private static int[]  getBestWay(int idx, int[] priority, int[][] board, int[][] kBoard, int[][] chkBoard) {
        int[] ways = new int[4];
        boolean[] isFirst = new boolean[4];
        Shark shark = sList[idx];
        int dir = shark.dir;
        int curX = sPoint.get(idx).x;
        int curY = sPoint.get(idx).y;

        for (int i = 0; i < priority.length; i++) {
            int nx = curX + direction[priority[i]][0];
            int ny = curY + direction[priority[i]][1];
            if (isValid(nx, ny)) {
                if (chkBoard[nx][ny] != shark.num + 1) {
                    isFirst[i] = true;
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (isFirst[i]) {
                list.add(priority[i]);
            }
        }

        for (int i = 0; i < 4; i++) {
            if (!isFirst[i]) list.add(priority[i]);
        }

        for (int i = 0; i < 4; i++) {
            ways[i] = list.get(i);
        }
        return ways;
    }

    private static void spreadSmell(int[][] board, int[][] kBoard, int[][] chkBoard) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 상어는 없지만 냄세는 남아있는 경우 냄세 1 감소
                if (board[i][j] == -1 && kBoard[i][j] > 0) {
                    kBoard[i][j] -= 1;
                    // 냄세가 아예 지워진 경우 상어 정보 삭제
                    if (kBoard[i][j] == 0) {
                        chkBoard[i][j] = -1;
                    }
                // 상어가 있는 경우 냄세를 뿌린다.
                } else if (board[i][j] > 0) {
                    kBoard[i][j] = K;
                    chkBoard[i][j] = board[i][j];
                }
            }
        }
    }


    private static boolean isValid(int x, int y) {
        if (x < 0 || y < 0 || x > N-1 || y > N - 1) return false;
        return true;
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sNum = board[i][j];
                String d = (sNum != -1) ? getDir(sList[sNum - 1].dir) : "N";
                System.out.print(board[i][j]+"/" +kBoard[i][j] +"/"+ chkBoard[i][j]+"/" + d+" ");
            }
            System.out.println();
        }
    }

    private static String getDir(int i) {
        if (i == 0) return "상";
        if (i == 1) return "하";
        if (i == 2) return "좌";
        return "우";
    }

    static class Shark implements Comparable<Shark> {
        private int num;
        private int dir;
        private int[][] dList;
        private boolean isAlive;
        private int x;
        private int y;

        public Shark(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }

        public Shark(int num, int dir, int[][] dList, boolean isAlive) {
            this.num = num;
            this.dir = dir;
            this.dList = dList;
            this.isAlive = isAlive;
        }

        public int compareTo(Shark o) {
            return this.num - o.num;
        }
    }
}
