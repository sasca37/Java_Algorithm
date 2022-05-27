import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashSet<String> hs = new HashSet<>(Arrays.asList(phone_book));
        for (String s : phone_book) {
            for (int j = 0; j < s.length(); j++) {
                if (hs.contains(s.substring(0, j))) return false;
            }
        }
        return true;
    }
}