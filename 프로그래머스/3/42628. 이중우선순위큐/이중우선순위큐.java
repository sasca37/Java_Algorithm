import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>((o1,o2)-> o2 - o1);
        
        for (int i=0; i < operations.length; i++) {
            String line = operations[i];
            
            StringTokenizer st = new StringTokenizer(line, " ");
            
            String command = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            if (command.equals("I")) {
                minPq.add(value);
                maxPq.add(value);
            } else {
                if (minPq.size() == 0) continue;
                int curMin = minPq.peek();
                int curMax = maxPq.peek();
                
                if (value == 1) {
                    minPq.remove(maxPq.poll());
                } else {
                    maxPq.remove(minPq.poll());
                }
            }

        }
                    
            if (!maxPq.isEmpty()) {
                answer[0] = maxPq.poll();
                answer[1] = minPq.poll();
            }
        
        return answer;
    }
}