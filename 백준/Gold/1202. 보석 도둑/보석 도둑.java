import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static long ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Jewelry[] jewelries = new Jewelry[N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            jewelries[i] = new Jewelry(weight,price);
        }
        Arrays.sort(jewelries);
        int[] bags = new int[K];
        for (int i = 0; i < K; i++) bags[i] = Integer.parseInt(br.readLine());
        Arrays.sort(bags);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0, j = 0; i < K; i++) {
            while (j < N && jewelries[j].weight <= bags[i]) {
                pq.offer(jewelries[j++].value);
            }

            if (!pq.isEmpty()) ans += pq.poll();
        }
        System.out.println(ans);
    }

}

class Jewelry implements Comparable<Jewelry> {
    int weight, value;

    public Jewelry(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(Jewelry o) {
        if (this.weight == o.weight) {
            return o.value - this.value;
        }
        return this.weight - o.weight;
    }
}