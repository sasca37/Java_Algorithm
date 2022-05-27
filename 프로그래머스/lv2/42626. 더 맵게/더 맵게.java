import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int x : scoville) pq.offer(x);

        while (!pq.isEmpty()) {
            int a = pq.poll();
            if (a >= K) {
                return answer;
            }
            if (pq.isEmpty()) return -1;
            int b = pq.poll();
            int tmp = a + b * 2;
            answer++;
            pq.offer(tmp);
        }
        return -1;
    }
}