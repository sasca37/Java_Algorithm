import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, arr[];
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        backtracking(1);
        System.out.println(answer);
    }

    private static void backtracking(int level) {
        if(!isAvailable(level-1)) return;

        if (level == N+1) {
            answer++;
            return;
        }

        for (int i = 1; i <= N; i++) {
            arr[level] = i;
            backtracking(level+1);
        }
    }

    private static boolean isAvailable(int row) {
        for (int i = 1; i < row; i++) {
            if (arr[row] == arr[i] || row-i == Math.abs(arr[row]-arr[i])) return false;
        }
        return true;
    }
}