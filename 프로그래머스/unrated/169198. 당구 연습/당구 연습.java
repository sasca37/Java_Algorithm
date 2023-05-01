class Solution {
         public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {

            int targetX = balls[i][0];
            int targetY = balls[i][1];

            int targetYUp = pow(startX - targetX) + pow(2 * n - startY - targetY);
            int targetYDown = pow(startX - targetX) + pow(startY + targetY);
            int targetXLeft = pow(startX + targetX) + pow(startY - targetY);
            int targetXRight = pow(startY - targetY) + pow(2 * m - startX - targetX);

            // 상 점대칭 불가
            if (startX == targetX && targetY > startY) {
                answer[i] = min(targetYDown, targetXLeft, targetXRight);
            }
            // 하 점대칭 불가
            else if (startX == targetX && targetY < startY) {
                answer[i] = min(targetYUp, targetXLeft, targetXRight);
            }
            // 좌 점대칭 불가
            else if (startY == targetY && targetX > startX) {
                answer[i] = min(targetYUp, targetYDown, targetXLeft);
            }
            // 우 점대칭 불가
            else if (startY == targetY && targetX < startX) {
                answer[i] = min(targetYUp, targetYDown, targetXRight);
            } else {
                answer[i] = min(targetYUp, targetYDown, targetXLeft, targetXRight);
            }
        }
        return answer;
    }

    public int pow(int num) {
        return (int) Math.pow(num, 2);
    }

    public int min(int... num) {
        int min = Integer.MAX_VALUE;
        for (int n : num) {
            min = Math.min(min, n);
        }
        return min;
    }
}