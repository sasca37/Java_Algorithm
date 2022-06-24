import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N + 1];
        int[] wait = new int[N + 1];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        StringTokenizer st = null;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());
            numbers[i] = value;
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int cur = Integer.parseInt(st.nextToken());
                graph.get(cur).add(i);
                wait[i]++;
            }
        }
        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for (int i = 1; i <= N; i++) {
            if (wait[i] == 0) pq.add(new int[]{i,numbers[i]});
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            answer = Math.max(cur[1], answer);
            for (int next : graph.get(cur[0])) {
                if (wait[next] == 1) {
                    pq.add(new int[]{next, numbers[next] + answer});
                }
                wait[next]--;
            }
        }

        System.out.println(answer);
    }

}