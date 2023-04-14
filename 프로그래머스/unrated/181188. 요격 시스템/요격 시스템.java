import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> a[1] - b[1]); // 끝나는 시간 기준으로 오름차순 정렬
        int cnt = 0, prevEnd = -1;
        for (int[] target : targets) {
            if (prevEnd <= target[0]) { // 이전에 처리한 미사일이 현재 미사일의 범위에 포함되지 않을 때
                cnt++; // 요격 미사일 수 증가
                prevEnd = target[1]; // 현재 미사일의 끝나는 시간으로 갱신
            }
        }
        return cnt;
    }
}