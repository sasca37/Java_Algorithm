import java.util.*;
class Solution {
 static ArrayList<String> answer = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        boolean[] visited = new boolean[tickets.length];
        String start = "ICN";
        String word = "ICN";
        dfs(start, word, tickets, visited, 0);
        Collections.sort(answer);
        String[] ans = answer.get(0).split(" ");
        return ans;
    }

    private static void dfs(String start, String word, String[][] tickets, boolean[] visited, int cnt) {
        if (cnt == tickets.length) {
            answer.add(word);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(start)) {
                visited[i] = true;
                dfs(tickets[i][1], word + " " + tickets[i][1], tickets, visited, cnt + 1);
                visited[i] = false;
            }
        }
    }
}