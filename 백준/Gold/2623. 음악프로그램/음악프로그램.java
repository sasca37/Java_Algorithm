import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] wait = new int[N + 1];
        int[] numbers = new int[N + 1];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] num = new int[n];
            for (int j = 0; j < n; j++) num[j] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    wait[num[k]]++;
                    graph.get(num[j]).add(num[k]);
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (wait[i] == 0) queue.add(i);
        }

        int idx = 1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            numbers[idx++] = cur;
            for (int next : graph.get(cur)) {
                if (wait[next] == 1) {
                    queue.add(next);
                }
                wait[next]--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (numbers[i] == 0) {
                System.out.println(0);
                return;
            }
            else sb.append(numbers[i]).append("\n");
        }
        System.out.println(sb);
    }

}