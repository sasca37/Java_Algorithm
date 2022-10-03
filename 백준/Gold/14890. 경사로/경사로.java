import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[][] board;
    static List<List<Integer>> loads = new ArrayList<>();
    static boolean[] bridges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for (int i = 0; i < N * 2; i++) {
            loads.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int curNum = Integer.parseInt(st.nextToken());
                board[i][j] = curNum;
                loads.get(i).add(curNum);
                loads.get(N+j).add(curNum);
            }
        }

        int ans = 0;
        for (int i = 0; i < N * 2; i++) {
            if (isLoad(i)) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static boolean isLoad(int n) {
        List<Integer> load = loads.get(n);
        bridges = new boolean[load.size()];
        int beforeHeight = load.get(0);

        for (int i = 1; i < load.size(); i++) {
            if(bridges[i]) continue;
            int curHeight = load.get(i);
            int diff = beforeHeight - curHeight;
            int diffAbs = Math.abs(diff);
            // 높이차이가 1이상인 경우
            if (diffAbs > 1) {
                return false;
            } else if (diffAbs == 1) {
                // 경사로를 둘 곳이 높이가 일치하지 않는 경우, 경사로를 둘 수 없는 경우
                if (isPossibleToMakeBridge(n, i, curHeight, diff)) {
                    // 높이가 작아진 경우
                    if (diff == 1) {
                        beforeHeight = curHeight;
                    } else {
                        beforeHeight++;
                    }
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isPossibleToMakeBridge(int lineNum, int idx, int curNum, int diff) {

        // 지나갈 길에 세우는 경우 (높이가 작아진 경우)
        if (diff == 1) {
            if (idx + L - 1 > N - 1) return false;
            for (int i = idx; i < idx + L; i++) {
                if (curNum != loads.get(lineNum).get(i)) {
                    return false;
                }
            }
            for (int i = idx; i < idx + L; i++) {
                bridges[i] = true;
            }

        }
        // 지나온 길에 세우는 경우 (높이가 커진 경우)
        else {
            idx -= 1;
            if (idx - L + 1 < 0) return false;
            for (int i = idx; i >= idx - L + 1; i--) {
                if (curNum-1 != loads.get(lineNum).get(i) || bridges[i]) {
                    return false;
                }
            }
            for (int i = idx; i > idx - L; i--) {
                bridges[i] = true;
            }
        }

        return true;
    }

}
