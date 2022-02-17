import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, arr[];
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        chk(0);
        System.out.println(answer);
    }

    private static void chk(int level) {
        if (level == N) {
            answer++;
            return;
        }

        for (int i=0; i<N; i++) {
            arr[level] = i;
            if (isPossible(level)) {
                chk(level+1);
            }
        }
    }

    private static boolean isPossible(int level) {
        for (int i = 0; i < level; i++) {
            if (arr[level] == arr[i]) return false;
            if (Math.abs(level -i) == Math.abs(arr[level] - arr[i])) return false;
        }
        return true;
    }
}