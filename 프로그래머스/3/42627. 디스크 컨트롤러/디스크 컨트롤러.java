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

PriorityQueue<Job> pq = new PriorityQueue<>( (o1, o2) -> o1.time - o2.time );

        int curEndTime = 0; // 현재 종료 시간

        int idx = 0;
        int i = 0;

        while (idx < jobs.length) {
            while (i < jobs.length && jobs[i][0] <= curEndTime) {
                pq.add(new Job(jobs[i][0], jobs[i][1]));
                i++;
            }
            if(pq.isEmpty()) {
                curEndTime = jobs[i][0];
            } else {
                Job tmp = pq.poll();
                answer += tmp.time + curEndTime - tmp.start;
                curEndTime += tmp.time;
                idx++;
            }
        }


        return answer/ jobs.length;
    }
     static class Job {
        int start;
        int time;
        
        public Job(int s, int t) {
            this.start = s;
            this.time = t;
        }
    }
}