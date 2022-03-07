import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N, parents[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        makeSet();
        ArrayList<City> citys = new ArrayList<>();
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            citys.add(new City(from, to, weight));
        }
        Collections.sort(citys);
        int cnt = 0;
        int answer= 0;
        int temp = 0;
        for (City x : citys) {
            if (union(x.from, x.to)) {
                answer += x.weight;
                temp = x.weight;
                if (++cnt == N-1) break;
            }
        }
        System.out.println(answer - temp);
    }

    private static void makeSet() {
        parents = new int[N+1];
        for (int i=1; i<=N; i++) parents[i] = i;
    }

    private static int findSet(int a) {
        if ( a == parents[a]) return a;
        return parents[a] = findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
}

class City implements Comparable<City> {
    int from, to, weight;

    public City(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(City o) {
        return this.weight - o.weight;
    }
}