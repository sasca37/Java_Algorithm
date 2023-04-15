function solution(sequence, k) {
    let answer = [];
    
    let lt = 0;
    let rt = 0;
    let ptrLen = 1000001;
    let sum = 0;
    answer[0] = lt;
    answer[1] = rt;
    
    while ( rt < sequence.length && lt <= rt) {
        if (lt == rt) {
              sum = sequence[lt];
          } 
          
          if (sum == k) {
              
              if (ptrLen > rt - lt + 1) {
                  ptrLen = rt - lt + 1;
                  answer[0] = lt;
                  answer[1] = rt;
              }
              
              sum -= sequence[lt];
              
              if (rt + 1 < sequence.length) {
                sum += sequence[rt + 1];    
              }
              
              if (lt == rt) {
                  break;
              }
              
              lt++;
              rt++;
              
          } else if (sum > k) { 
              sum -= sequence[lt];
              lt++;
          } else if (sum < k) {
              if (rt + 1 < sequence.length) {
                sum += sequence[rt + 1];    
              }
              rt++;
          }
    }
    
    return answer;
}