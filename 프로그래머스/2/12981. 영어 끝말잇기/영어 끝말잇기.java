import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[]{0,0};
        
        Set<String> set = new HashSet<>();
        boolean isFinish = false;
        int round = 0;
        for (int i=0; i<words.length; i+=n) {
            round++;
            if (isFinish) break;
            for (int j=0; j < n; j++) {
                
                if (set.contains(words[i+j])) {
                    answer[0] = j+1;
                    answer[1] = round;
                    isFinish = true;
                    break;
                }
                set.add(words[i+j]);
                
                if (i+j != 0 && 
                    words[i+j - 1].charAt(words[i+j-1].length()-1) != words[i+j].charAt(0)) {
                    answer[0] = j+1;
                    answer[1] = round;
                    isFinish = true;
                    break;
                }
            }
        }
        
        return answer;
    }
}