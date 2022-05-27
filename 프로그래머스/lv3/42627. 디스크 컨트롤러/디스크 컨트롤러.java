import java.util.*;

class Solution {

    public int solution(int[][] jobs) {
        int answer = 0;
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int curEndTime = 0; // 현재 종료 시간

        int idx = 0;
        int i = 0;

        while (idx < jobs.length) {
            while (i < jobs.length && jobs[i][0] <= curEndTime) {
                pq.add(jobs[i++]);
            }
            if(pq.isEmpty()) {
                curEndTime = jobs[i][0];
            } else {
                int[] tmp = pq.poll();
                answer += tmp[1] + curEndTime - tmp[0];
                curEndTime += tmp[1];
                idx++;
            }
        }


        return answer/ jobs.length;
    }
}