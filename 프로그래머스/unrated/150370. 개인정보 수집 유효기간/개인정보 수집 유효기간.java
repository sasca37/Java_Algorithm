import java.util.*;
class Solution {
    private static Map<String, String> map = new HashMap<>();
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        List<Integer> ans = new ArrayList<>();

        for (int i=0; i<terms.length; i++) {
            String[] line = terms[i].split(" ");
            map.put(line[0], line[1]);
        }
        
        int todayToInt = getYearToIntByDay(today) + getMonthToIntByDay(today) + getDayToIntByDay(today);

        for (int i = 0; i < privacies.length; i++) {
            String[] line = privacies[i].split(" ");
            String curDay = line[0];
            int term = Integer.parseInt(map.get(line[1])) * 28;
            int curDayToInt = getYearToIntByDay(curDay) + getMonthToIntByDay(curDay) + getDayToIntByDay(curDay) + term;
            if (todayToInt >= curDayToInt) {
                ans.add(i+1);
            }
        }

        answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
    
    private int getYearToIntByDay(String day) {
        return Integer.parseInt(day.substring(0, 4)) * 28 * 12;
    }
    
    private int getMonthToIntByDay(String day) {
        return Integer.parseInt(day.substring(5, 7)) * 28;
    }
    
    private int getDayToIntByDay(String day) {
        return Integer.parseInt(day.substring(8, 10));
    }
}