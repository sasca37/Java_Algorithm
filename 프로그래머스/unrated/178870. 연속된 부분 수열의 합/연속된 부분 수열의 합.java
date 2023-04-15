import java.util.*;

class Solution {
  public int[] solution(int[] sequence, int k) {
      int lt = 0;
      int rt = 0;
      int ptrLen = Integer.MAX_VALUE;
      int sum = 0;
      int[] answer = new int[2];
      int[] ptrArr = new int[2];
      while (rt < sequence.length && lt <= rt) {
          
          if (lt == rt) {
              sum = sequence[lt];
          } 
          
          // System.out.println(lt +" " + rt +" " + sum);
          
          if (sum == k) {
              
              if (ptrLen > rt - lt + 1) {
                  ptrLen = rt - lt + 1;
                  answer[0] = lt;
                  answer[1] = rt;
              }
              sum -= sequence[lt];
              if (rt+1 < sequence.length) {
                sum += sequence[rt + 1];    
              }
              lt++;
              rt++;
              
          } else if (sum > k) { // lt ++
              sum -= sequence[lt];
              lt++;
          } else if (sum < k) {
              if (rt+1 < sequence.length) {
                sum += sequence[rt + 1];    
              }
              rt++;
          }
          
      }
      return answer;
  }
}