import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Deque<Truck> trucks = new ArrayDeque<>();
        ArrayList<Truck> bridges= new ArrayList<>();
        for (int i = 0; i < truck_weights.length; i++) {
            trucks.add(new Truck(truck_weights[i]));
        }
        int time = 0;
        int currentTotalWeight = 0;
        while (!trucks.isEmpty()) {
            time++;
            Truck tmp = trucks.peekFirst();

            if(bridges.size() != 0 && bridges.get(0).time == bridge_length) {
                currentTotalWeight -= bridges.get(0).weight;
                bridges.remove(0);
            }

            // 트럭을 넣을 수 있는 상황이라면
            if (bridges.size() < bridge_length && currentTotalWeight + tmp.weight <= weight) {
                currentTotalWeight += tmp.weight;
                bridges.add(trucks.pollFirst());
            }

            // 다리에 있는 모든 트럭 이동
            for (Truck x : bridges) {
                x.move();
            }
        }

        while (bridges.size() > 0) {
            time++;
            if(bridges.get(0).time == bridge_length) bridges.remove(0);
            for (Truck x : bridges) {
                x.move();
            }
        }
        return time;
    }

    static class Truck {
        int weight, time;

        public Truck(int weight) {
            this.weight = weight;
            this.time = 0;
        }

        public void move() {
            this.time++;
        }
    }
}