import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 올리는 위치 1 , 내리는 위치 N, 끝 2N
    static int N, K;
    static List<Integer> belt = new ArrayList<>();
    static List<Robot> robots = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        belt.add(-1);
        while (st.hasMoreTokens()) {
            belt.add(Integer.parseInt(st.nextToken()));
        }

        int ans = 0;
        while (true) {
            ans ++;
            turnBeltAndRobot();
            robotMove();
            raiseRobot();
            if (isFinish()) break;
        }
        System.out.println(ans);
    }

    // 1. 벨트가 각 칸 위에 있는 로봇과 함께 한칸 회전한다.
    private static void turnBeltAndRobot() {
        int lastValue = belt.remove(belt.size() - 1);
        belt.add(1, lastValue);
        int removeRobotIdx = -1;
        for (int i = 0; i < robots.size(); i++) {
            robots.get(i).beltIdx += 1;
            if (robots.get(i).beltIdx == N) {
                removeRobotIdx = i;
            }
        }

        if (removeRobotIdx >= 0) {
            robots.remove(removeRobotIdx);
        }
    }

    // 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 이동할 수 있다면 이동한다.
    private static void robotMove() {
        int removeIdx = -1;
        for (int i = 0; i< robots.size(); i++) {
            if (robots.get(i).moveAndRemove(i) != -1) {
                removeIdx = i;
            }
        }
        if (removeIdx != -1) robots.remove(removeIdx);
    }

    // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
    private static void raiseRobot() {
        if (belt.get(1) > 0) {
            robots.add(new Robot());
            decreaseIdx(1);
        }
    }

    private static boolean isFinish() {
        int cnt = 0;
        for (int idx : belt) {
            if (idx == 0) cnt++;
        }
        if (cnt >= K) return true;
        return false;
    }

    private static class Robot {
        private int beltIdx;

        public Robot() {
            this.beltIdx = 1;
        }

        public int moveAndRemove(int idx) {
            int nextBeltIdx = getNextBeltIdx(beltIdx);
            boolean isRemove = false;
            if (belt.get(nextBeltIdx) > 0 && notExistRobot(nextBeltIdx)) {
                beltIdx = nextBeltIdx;
                if (nextBeltIdx == N) {
                    isRemove = true;
                }
                decreaseIdx(beltIdx);
            }
            if (isRemove) return idx;
            return -1;
        }
    }

    private static int getNextBeltIdx(int idx) {
        if (idx == 2 * N) return 1;
        return idx+1;
    }

    private static boolean notExistRobot(int idx) {
        for (Robot robot : robots) {
            if (robot.beltIdx == idx) return false;
        }
        return true;
    }

    private static void decreaseIdx(int idx) {
        int curValue = belt.remove(idx);
        belt.add(idx, curValue - 1);
    }
}

