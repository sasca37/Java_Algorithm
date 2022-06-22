import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N+1];
        int[] wait = new int[N+1];
        int[] answer = new int[N+1];

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            numbers[i] = num;
            while (st.hasMoreTokens()) {
                int before = Integer.parseInt(st.nextToken());
                if (before == -1) continue;
                graph.get(before).add(i);
                wait[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (wait[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                answer[next] = Math.max(answer[next], answer[cur] + numbers[cur]);
                wait[next]--;
                if (wait[next] == 0) queue.offer(next);
            }
        }

        for (int i=1; i<=N; i++) {
            sb.append(answer[i] + numbers[i]).append("\n");
        }
        System.out.println(sb);
    }

}