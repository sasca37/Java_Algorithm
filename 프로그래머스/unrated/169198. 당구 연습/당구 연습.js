let Ball = class {
    constructor(startX, startY) {
        this.startX = startX;
        this.startY = startY;
    }
  
    getMin() {
        const startX = this.startX;
        const startY = this.startY;
        const targetX = this.targetX;
        const targetY = this.targetY;
        const m = this.m;
        const n = this.n; 

        const up = Math.pow(startX - targetX, 2) + Math.pow(2 * n - startY - targetY, 2);
        const down = Math.pow(startX - targetX, 2) + Math.pow(startY + targetY, 2);
        const left = Math.pow(startX + targetX, 2) + Math.pow(startY - targetY, 2);
        const right = Math.pow(startY - targetY, 2) + Math.pow(2 * m - startX - targetX, 2);

        // 상 점대칭 불가
        if (startX == targetX && targetY > startY) {
            return Math.min(down, left, right);
        }
        // 하 점대칭 불가
        else if (startX == targetX && targetY < startY) {
            return Math.min(up, left, right);
        }
        // 좌 점대칭 불가
        else if (startY == targetY && targetX > startX) {
            return Math.min(up, down, left);
        }
        // 우 점대칭 불가
        else if (startY == targetY && targetX < startX) {
            return Math.min(up, down, right);
        } else {
            return Math.min(up, down, left, right);
        }
    }
};

function solution(m, n, startX, startY, balls) {
    var answer = [];

    const ball = new Ball(startX, startY);

    for ( let [targetX, targetY] of balls) {
        ball.m = m;
        ball.n = n;
        ball.targetX = targetX;
        ball.targetY = targetY;
        answer.push(ball.getMin());
    }
    return answer;
}