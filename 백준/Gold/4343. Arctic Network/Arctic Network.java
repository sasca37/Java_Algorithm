import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N,M, parents[];
    static ArrayList<Edge> subEdges;
    static PriorityQueue<Edge> edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            subEdges = new ArrayList<>();
            edges = new PriorityQueue<>();
            makeSet();
            for (int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                subEdges.add(new Edge(from,to));
            }
            for (int i=0; i<subEdges.size()-1; i++) {
                for (int j=i+1; j<subEdges.size(); j++) {
                    edges.add(new Edge(i,j,getWeight(subEdges.get(i),subEdges.get(j))));
                }
            }
            int cnt = 0;
            double ans = 0;
            while (!edges.isEmpty()) {
                Edge tmp = edges.poll();
                if (union(tmp.from, tmp.to)) {
                    if(++cnt == M-N) ans = tmp.weight;
                }
            }
            sb.append(String.format("%.2f",ans)).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    private static int findSet(int a) {
        if (parents[a] == -1) return a;
        return parents[a] = findSet(parents[a]);
    }

    private static double getWeight(Edge a, Edge b) {
        double dis = Math.pow(a.from - b.from, 2) + Math.pow(a.to - b.to, 2);
        double ans = Math.sqrt(dis);
        return Math.round(ans * 100) / 100.0;
    }

    private static void makeSet() {
        parents = new int[M];
        for (int i=0; i<M; i++) parents[i] = -1;
    }


}

class Edge implements Comparable<Edge> {
    int from,to;
    double weight;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public Edge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        if( this.weight > o.weight) return 1;
        else return -1;
    }
}