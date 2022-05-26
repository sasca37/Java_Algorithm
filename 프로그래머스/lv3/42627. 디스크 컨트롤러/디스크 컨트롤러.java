import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        ArrayList<Disk> arr = new ArrayList<>();
        for (int i = 0; i < jobs.length; i++) {
            arr.add(new Disk(jobs[i][0], jobs[i][1]));
        }

        Collections.sort(arr, new Comparator<Disk>() {
            @Override
            public int compare(Disk o1, Disk o2) {
                if(o1.startTime == o2.startTime) return o1.requiredTime - o2.requiredTime;
                return o1.startTime - o2.startTime;
            }
        });

        PriorityQueue<Disk> pq = new PriorityQueue<>(new Comparator<Disk>() {
            @Override
            public int compare(Disk o1, Disk o2) {
                return o1.requiredTime - o2.requiredTime;
            }
        });

        int answer = 0;
        int cnt = 0;
        int now = 0;

        int i = 0;
        while (cnt < arr.size()) {
            while (i < arr.size() && arr.get(i).startTime <= now) {
                pq.add(arr.get(i++));
            }
            if (pq.isEmpty()) {
                now = arr.get(i).startTime;
            } else {
                Disk tmp = pq.poll();
                answer += tmp.requiredTime + now - tmp.startTime;
                now += tmp.requiredTime;
                cnt++;
            }
        }

        return answer / arr.size();
    }

    static class Disk{
        int startTime, requiredTime;

        public Disk(int startTime, int requiredTime) {
            this.startTime = startTime;
            this.requiredTime = requiredTime;
        }
    }
}