import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N,M, parents[], totalCnt;
    static ArrayList<Edge> edges = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        makeSet();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if(union(from, to)) totalCnt++;
        }
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=N; j++) {
                int weight = Integer.parseInt(st.nextToken());
                if (i != 1 && i < j) edges.add(new Edge(i, j, weight));
            }
        }
        Collections.sort(edges);

        int cnt = 0;
        int result = 0;
        StringBuilder sb = new StringBuilder();
        for (Edge x : edges) {
            if (union(x.from, x.to)) {
                totalCnt++;
                cnt++;
                result += x.weight;
                sb.append(x.from).append(" ").append(x.to).append("\n");
                if (totalCnt == N-1) break;
            }
        }
        System.out.println(result +" " + cnt);
        System.out.println(sb);
    }

    private static int findSet(int a) {
        if(parents[a] == a) return a;
        return parents[a] = findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    private static void makeSet() {
        parents = new int[N+1];
        for (int i=1; i<=N; i++) parents[i] = i;
    }
}

class Edge implements Comparable<Edge> {
    int from, to, weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}