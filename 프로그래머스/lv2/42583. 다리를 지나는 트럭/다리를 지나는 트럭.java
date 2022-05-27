import java.util.*;

class Solution {
    static int[] bridge;
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < truck_weights.length; i++) {
            deque.offer(truck_weights[i]);
        }

        bridge = new int[bridge_length];

        while (!deque.isEmpty()) {
            answer++;
            int truck = deque.peek();
            changeBridge();
            int currentWeight = getWeight();
            if (currentWeight + truck <= weight && bridge[bridge_length-1] == 0) {
                add(deque.pollFirst());
            }
        }
        while(true) {
            if(isAllClear()) {
                break;
            }
            answer++;
            changeBridge();
        }
        return answer;
    }

    private static boolean isAllClear() {
        for (int i = 0; i < bridge.length; i++) {
            if (bridge[i] != 0) return false;
        }
        return true;
    }

    private static void add(int truck) {
        bridge[bridge.length-1] = truck;
    }

    private static void changeBridge() {
        for (int i = 0; i < bridge.length - 1; i++) {
            bridge[i] = bridge[i + 1];
        }
        bridge[bridge.length-1] = 0;
    }

    private static int getWeight() {
        int total = 0;
        for (int x : bridge) total += x;
        return total;
    }
}