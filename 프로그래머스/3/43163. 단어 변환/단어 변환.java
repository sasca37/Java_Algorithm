import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        boolean[] visited = new boolean[words.length];
        
        Queue<Data> que = new LinkedList<>();
        que.add(new Data(begin, 0));
        
        while(!que.isEmpty()) {
            Data data = que.poll();
            if (data.word.equals(target)) {
                answer = data.cnt;
                break;
            }
            for (int i=0; i < words.length; i++) {
                if (!visited[i] && isOneDiffWord(data.word, words[i])) {
                    visited[i] = true;
                    que.add(new Data(words[i], data.cnt + 1));
                }
            }
        }
        
        return answer;
    }
    
    public boolean isOneDiffWord(String s, String e) {
        int cnt = 0;
        for (int i=0; i < s.length(); i++) {
            if (s.charAt(i) == e.charAt(i)) {
                cnt++;
            }
        }
        
        if (cnt == s.length() - 1 ) {
            return true;
        }
        
        return false;
        
    }
    
    static class Data {
        String word;
        int cnt;
        
        public Data(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
}