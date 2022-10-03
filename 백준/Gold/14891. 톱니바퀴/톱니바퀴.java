import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<List<Integer>> gears = new ArrayList<>();
    static boolean isPossibleToTurn[] = new boolean[4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        for (int i=0; i<4; i++) {
            char[] readLines = br.readLine().toCharArray();
            gears.add(new ArrayList<>());
            for (int j=0; j<readLines.length; j++) {
                gears.get(i).add(readLines[j]-'0');
            }
        }

        int turns = Integer.parseInt(br.readLine());
        for (int i = 0; i < turns; i++) {
            st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());
            isPossibleToTurn = getTurnArrays(gearNum);
            move(gearNum, direction);
        }

        int ans = 0;
        for (int i = 0; i < gears.size(); i++) {
            if (gears.get(i).get(0) == 1) {
                ans += getScore(i+1);
            }
        }
        System.out.println(ans);
    }

    private static boolean[] getTurnArrays(int num) {
        boolean[] arrays = new boolean[4];
        int leftNum = num - 1;
        int rightNum = num + 1;
        while (leftNum >= 0) {
            if (gears.get(leftNum).get(2) != gears.get(leftNum + 1).get(6)) {
                arrays[leftNum] = true;
                leftNum--;
            }
            else break;
        }

        while (rightNum <= 3) {
            if (gears.get(rightNum).get(6) != gears.get(rightNum - 1).get(2)) {
                arrays[rightNum] = true;
                rightNum++;
            }
            else break;
        }
        return arrays;
    }

    private static int getScore(int n) {
        if (n == 1) return 1;
        else if (n==2) return 2;
        else if (n == 3) return 4;
        else return 8;
    }
    private static void print() {
        for (int i = 0; i < gears.size(); i++) {
            for (int j = 0; j < gears.get(i).size(); j++) {
                int cur = gears.get(i).get(j);
                if (cur == 0) {
                    System.out.print("N");
                }
                else System.out.print("S");
            }
            System.out.println();
        }
    }

    private static void move(int gearNum, int direction) {
        turn(gearNum, direction);
        int curLeftNum = gearNum-1;
        int curRightNum = gearNum+1;
        int curDirection = direction;
        while (curLeftNum >= 0 && isPossibleToTurn[curLeftNum]) {
            curDirection *= -1;
            turn(curLeftNum, curDirection);
            curLeftNum--;
        }
        curDirection = direction;
        while (curRightNum <= 3 && isPossibleToTurn[curRightNum]) {
            curDirection *= -1;
            turn(curRightNum, curDirection);
            curRightNum++;
        }
    }

    private static void turn(int gearNum, int direction) {
        // 시계
        if (direction == 1) {
            int lastNum = gears.get(gearNum).get(7);
            gears.get(gearNum).add(0, lastNum);
            gears.get(gearNum).remove(8);
        } else {
            int firstNum = gears.get(gearNum).get(0);
            gears.get(gearNum).remove(0);
            gears.get(gearNum).add(firstNum);
        }
    }
}
