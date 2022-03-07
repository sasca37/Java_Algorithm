import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, parents[];
    static ArrayList<Planet> subEdges = new ArrayList<>();
    static PriorityQueue<Planet> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        makeSet();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            subEdges.add(new Planet(i,x,y,z));
        }
        Collections.sort(subEdges, new Comparator<Planet>() {
            @Override
            public int compare(Planet o1, Planet o2) {
                return o1.from - o2.from;
            }
        });
        for (int i = 0; i < N - 1; i++) pq.offer(new Planet(subEdges.get(i).index, subEdges.get(i+1).index, getWeight(subEdges.get(i).from, subEdges.get(i+1).from)));

        Collections.sort(subEdges, new Comparator<Planet>() {
            @Override
            public int compare(Planet o1, Planet o2) {
                return o1.to - o2.to;
            }
        });

        for (int i = 0; i < N - 1; i++) pq.offer(new Planet(subEdges.get(i).index, subEdges.get(i+1).index, getWeight(subEdges.get(i).to, subEdges.get(i+1).to)));

        Collections.sort(subEdges, new Comparator<Planet>() {
            @Override
            public int compare(Planet o1, Planet o2) {
                return o1.weight - o2.weight;
            }
        });

        for (int i = 0; i < N - 1; i++) pq.offer(new Planet(subEdges.get(i).index, subEdges.get(i+1).index, getWeight(subEdges.get(i).weight, subEdges.get(i+1).weight)));

        int cnt = 0;
        int ans = 0;
        while (!pq.isEmpty()) {
            Planet x = pq.poll();
            if (union(x.from, x.to)) {
                ans += x.weight;
                if(++cnt == N-1) break;
            }
        }
        System.out.println(ans);
    }

    private static void makeSet() {
        parents = new int[N];
        for (int i=0; i<N; i++) parents[i] = i;
    }


    private static int getWeight(int a, int b) {
        return Math.abs(a-b);
    }

    public static int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }

    public static boolean union(int x, int y) {
        int aRoot = find(x);
        int bRoot = find(y);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

}

class Planet implements Comparable<Planet> {
    int index, from, to, weight;

    public Planet(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Planet(int index, int from, int to, int weight) {
        this.index = index;
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Planet o) {
        return this.weight - o.weight;
    }
}
