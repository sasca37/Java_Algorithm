import java.util.*;
class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        Arrays.sort(weights);
        
        
        Map<Double, Integer> map = new HashMap<>();
        
        // 2 / 3, 2 /4, , 3 / 4
        
        for (int num : weights) {
            
            double x1 = num;
            double x2 = num * 1.0 * 2 / 3;
            double x3 = num * 1.0 * 3 / 4;
            double x4 = num * 1.0 * 1 / 2;
            
            if (map.containsKey(x1)) answer += map.get(x1);
            if (map.containsKey(x2)) answer += map.get(x2);
            if (map.containsKey(x3)) answer += map.get(x3);
            if (map.containsKey(x4)) answer += map.get(x4);
            map.put((num*1.0), map.getOrDefault((num*1.0), 0)+1);
        }
        
        return answer;
    }
}

