import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, P, parents[];
    static HashSet<Integer> stations = new HashSet<>();
    static PriorityQueue<Station> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < P; i++) stations.add(Integer.parseInt(st.nextToken()));
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.offer(new Station(from, to, weight));
        }
        makeSet();
        // 간선 범위 : N-P ~ N-1
        int minEdge = N-P;
        int cnt = 0;
        int ans = 0;
        while (!pq.isEmpty()) {
            Station tmp = pq.poll();
            if (union(tmp.from, tmp.to)) {
                cnt++;
                ans+= tmp.weight;
                if (cnt >= minEdge && isAllConnect()) {
                    break;
                }

            }
        }
        System.out.println(ans);
    }

    private static boolean isAllConnect() {
        for (int i=2; i<=N; i++) {
            if(!stations.contains(findSet(i))) return false;
        }
        return true;
    }

    private static void makeSet() {
        parents = new int[N+1];
        for (int i=1; i<=N; i++) parents[i] = i;
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        boolean isAStation = stations.contains(aRoot);
        boolean isBStation = stations.contains(bRoot);
        if(aRoot == bRoot || (isAStation && isBStation)) return false;
        if(isAStation) parents[bRoot] = aRoot;
        else if (isBStation) parents[aRoot] = bRoot;
        else parents[bRoot] = aRoot;
        return true;
    }

    private static int findSet(int a) {
        if (a == parents[a]) return a;
        return parents[a] = findSet(parents[a]);
    }
}

class Station implements Comparable<Station> {
    int from,to, weight;

    public Station(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Station o) {
        return this.weight - o.weight;
    }
}