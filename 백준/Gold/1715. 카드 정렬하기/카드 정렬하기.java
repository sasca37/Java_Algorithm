import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        int N = Integer.parseInt(br.readLine());


        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(br.readLine());
            pq.add(tmp);
        }
        if (N == 1) {
            System.out.println(0);
        }
        else {
            long answer = 0;
            while (!pq.isEmpty()) {
                if(pq.size() > 1) {
                    int a = pq.poll();
                    int b = pq.poll();
                    pq.add(a+b);
                    answer += a+b;
                } else break;
            }
            System.out.println(answer);
        }

    }
}