import java.util.*;
class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        Map<String, Integer> scoreMap = new HashMap<>();
        for (int i=0; i<name.length; i++) {
            scoreMap.put(name[i], yearning[i]);
        }
        
        for (int i=0; i<photo.length; i++) {
            int total = 0;
            for (int j=0; j < photo[i].length; j++) {
                Integer getScore = scoreMap.get(photo[i][j]);
                if (getScore != null) {
                    total += getScore;
                }
            }
            answer[i] = total;
        }
        
        return answer;
    }
}