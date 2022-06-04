import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int K;
    static int[] arr;
    static ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        K = Integer.parseInt(br.readLine());
        arr = new int[(int) Math.pow(2, K) - 1];

        for (int i = 0; i < K; i++) answer.add(new ArrayList<>());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int idx = 0;
        while (st.hasMoreTokens()) {
            arr[idx++] = Integer.parseInt(st.nextToken());
        }

        dfs(0, arr.length - 1, 0);

        for (int i = 0; i < K; i++) {
            for (int x : answer.get(i)) {
                sb.append(x).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int start, int end, int depth) {
        if (depth == K) return;
        if (start == end) {
            answer.get(depth).add(arr[start]);
            return;
        }

        int mid = (start + end) / 2;
        answer.get(depth).add(arr[mid]);

        dfs(start, mid - 1, depth + 1);
        dfs(mid + 1, end, depth + 1);
    }
}

