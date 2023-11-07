import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        int zeroRemoveCnt = 0;
        int cycle = 0;
        
        while (!s.equals("1")) {
            cycle++;
            int t = 0;
            int beforeLen = s.length();
            s = s.replaceAll("0", "");

            
            int afterLen = s.length(); 
            zeroRemoveCnt += beforeLen - afterLen;
            
            t = afterLen;
            
            String word = "";
            
            while(t != 1) {
                word += t % 2;
                t /= 2;
            }
            word += "1";
            //System.out.println(s);
            s = "";
            for (int i=word.length() - 1; i >= 0; i--) {
                s += word.charAt(i);
            }
            //System.out.println(s);
            //break;
        }
        
        answer[0] = cycle;
        answer[1] = zeroRemoveCnt;
        
        return answer;
    }
}