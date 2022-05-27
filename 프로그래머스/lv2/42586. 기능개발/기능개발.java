import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        Deque<Job> deque = new ArrayDeque<>();

        int[] score = new int[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            deque.add(new Job(i, progresses[i]));
            score[i] = progresses[i];
        }

        while (!deque.isEmpty()) {
            Job tmp = deque.peekFirst();
            // 현재 작업이 100 이상이라면
            if (score[tmp.index] >= 100) {
                // 전부 출력
                int cnt = 0;
                while (true) {
                    if (deque.isEmpty()) break;
                    if (score[deque.peek().index] >= 100) {
                        deque.pollFirst();
                        cnt++;
                    }
                    else break;
                }
                answer.add(cnt);
            }
            // 하루 단위로 모든 작업 추가
            else {
                int curIdx = tmp.index;
                for (int i = curIdx; i < speeds.length; i++) {
                    score[i] += speeds[i];
                }
            }
        }
        return answer;
    }

    static class Job {
        int index, progress;

        public Job(int index, int progress) {
            this.index = index;
            this.progress = progress;
        }
    }
}