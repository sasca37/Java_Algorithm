import java.util.*;
class Solution {
        public int[] solution(String[] id_list, String[] report, int k) {
        HashSet<String> reports = new LinkedHashSet<>();
        for (int i=0; i<report.length; i++) {
            reports.add(report[i]);
        }
        String[] report2 = reports.toArray(String[]::new);

        int[] answer = new int[id_list.length];
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < report2.length; i++) {
            String[] word = report2[i].split(" ");
            hashMap.put(word[1], hashMap.getOrDefault(word[1], 0)+1);
        }
        Set<String> rockList = new HashSet<>();
        for (int i = 0; i < id_list.length; i++) {
            if (hashMap.get(id_list[i]) != null && hashMap.get(id_list[i]) >= k) {
                rockList.add(id_list[i]);
            }
        }

        for (int j = 0; j < report2.length; j++) {
            String word[] = report2[j].split(" ");
            if (rockList.contains(word[1])) {
                for (int i=0; i < id_list.length; i++) {
                    if (id_list[i].equals(word[0])) {
                        answer[i]++;
                    }
                }
            }
        }

        return answer;
    }
}