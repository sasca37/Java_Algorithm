import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> plusPQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        PriorityQueue<Integer> minusPQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } return -1;
            }
        });

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp > 0) plusPQ.add(tmp);
            else minusPQ.add(tmp);
        }

        int answer = 0;

        while (!plusPQ.isEmpty()) {
            if(plusPQ.size() > 1) {
                int a = plusPQ.poll();
                int b = plusPQ.poll();
                if (a == 1) {
                    answer += 2;
                } else if (b==1) {
                    answer += a + 1;
                } else {
                    answer += a*b;
                }
            } else {
                answer += plusPQ.poll();
            }
        }

        while (!minusPQ.isEmpty()) {
            if (minusPQ.size() > 1) {
                answer += minusPQ.poll() * minusPQ.poll();
            } else {
                answer += minusPQ.poll();
            }
        }

        System.out.println(answer);
    }
}