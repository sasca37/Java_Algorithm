import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] board;
    static int[] numbers;
    static int[] input;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        numbers = new int[N];
        for (int i=0; i<N; i++) numbers[i] = i;
        input = new int[N/2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0,0);
        System.out.println(answer);
    }

    private static void combination(int cnt, int start) {
        if (cnt == N/2) {
            ArrayList<Integer> linkArr = new ArrayList<>();
            int[] chkList = new int[N];
            for (int i=0; i<N/2; i++) {
                chkList[input[i]] = 1;
            }

            for (int i = 0; i < N; i++) {
                if(chkList[i] == 0) linkArr.add(i);
            }


            int startScore = 0;
            int linkScore = 0;
            for (int i = 0; i < N / 2 -1; i++) {
                for (int j = i + 1; j < N / 2; j++) {
                    int startX = input[i];
                    int startY = input[j];
                    startScore += board[startX][startY] + board[startY][startX];

                    int linkX = linkArr.get(i);
                    int linkY = linkArr.get(j);
                    linkScore += board[linkX][linkY] + board[linkY][linkX];
                }
            }
            answer = Math.min(answer, Math.abs(startScore-linkScore));
            return;
        }

        for (int i = start; i < N; i++) {
            input[cnt] = numbers[i];
            combination(cnt+1, i+1);
        }
    }
}
