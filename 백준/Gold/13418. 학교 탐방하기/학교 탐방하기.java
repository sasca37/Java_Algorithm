import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N,M, parents[];
    static ArrayList<Edge> edges = new ArrayList<>();
    static int startWeight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i=0; i<=M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if(i == 0) startWeight = weight;
            else edges.add(new Edge(from, to , weight));
        }
        // 내리막길이 먼저 오도록 정렬
        Collections.sort(edges);
        int a = getFatigue();
        Collections.sort(edges, Collections.reverseOrder());
        int b = getFatigue();
        System.out.println(b-a);
    }

    private static int getFatigue() {
        makeSet();
        // 오르막길 카운팅
        int uphillCnt = startWeight==0?1 : 0;
        int cnt = 0;
        for (Edge x : edges) {
            if (union(x.from, x.to)) {
                if (x.weight == 0) uphillCnt ++;
                if (++cnt == N-1) break;
            }
        }
        return uphillCnt * uphillCnt;
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    private static int findSet(int a) {
        if (parents[a] == -1) return a;
        return parents[a] = findSet(parents[a]);
    }

    private static void makeSet() {
        parents = new int[N+1];
        // 최초 부모노드는 자기 자신이므로 -1로 초기화
        for (int i=0; i<=N; i++) parents[i] = -1;
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
        // 내리막길이 먼저 오도록 정렬
        return (this.weight - o.weight) * -1;
    }
}