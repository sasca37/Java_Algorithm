import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, parents[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        makeSet();
        int M = Integer.parseInt(br.readLine());
        Computer[] computers = new Computer[M];
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            computers[i] = new Computer(from, to, weight);
        }
        Arrays.sort(computers);
        int cnt = 0;
        int answer= 0;
        for (Computer x : computers) {
            if (union(x.from, x.to)) {
                answer += x.weight;
                if (++cnt == N-1) break;
            }
        }
        System.out.println(answer);
    }

    private static void makeSet() {
        parents = new int[N+1];
        for (int i=1; i<=N; i++) parents[i] = i;
    }

    private static int findSet(int a) {
        if ( a == parents[a]) return a;
        return findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
}

class Computer implements Comparable<Computer> {
    int from, to, weight;

    public Computer(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Computer o) {
        return this.weight - o.weight;
    }
}