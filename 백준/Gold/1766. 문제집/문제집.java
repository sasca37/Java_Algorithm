import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int[] indegree = new int[N + 1];
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i = 0; i <= N; i++) arr.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            indegree[end]++;
            arr.get(start).add(end);
        }

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) pq.add(i);
        }

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            int num = pq.poll();
            for (int next : arr.get(num)) {
                if (indegree[next] > 0) {
                    if (indegree[next] == 1) {
                        indegree[next] = 0;
                        pq.add(next);
                    }
                    else indegree[next]--;
                }
            }
            sb.append(num).append(" ");
        }

        System.out.println(sb);
    }

}