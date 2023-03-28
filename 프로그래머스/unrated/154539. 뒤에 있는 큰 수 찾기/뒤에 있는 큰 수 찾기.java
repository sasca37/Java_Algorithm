import java.util.*;

class Solution {
     public int[] solution(int[] numbers) {
        // number index 정보를 담을 Stack 생성
        Stack<Integer> st = new Stack<>();

        // 정답 배열 생성
        int[] answer = new int[numbers.length];

        // 첫 번째 number 인덱스 stack에 push
        st.push(0);

        // 두 번째 원소부터 numbers 탐색
        for (int i = 1; i < numbers.length; i++) {
            // 스택이 비어있지 않으면
            while (!st.isEmpty() && numbers[st.peek()] < numbers[i]) {
                
                // numbers 의 현재 원소 값이 스택이 바라보고 있는 실제 값보다 큰 경우 : 뒤에 있는 큰 수 해당
                answer[st.pop()] = numbers[i];
            }
            st.push(i);


        }
        
        while (!st.isEmpty()) {
            answer[st.pop()] = -1;
        }


        return answer;
    }
}