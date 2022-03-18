import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, parents[];
    static double arr[][];
    static ArrayList<Star> stars = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        arr = new double[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double from = Double.parseDouble(st.nextToken());
            double to = Double.parseDouble(st.nextToken());
            arr[i][0] = from;
            arr[i][1] = to;
        }
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                stars.add(new Star(i,j, getWeight(i,j)));
            }
        }
        Collections.sort(stars, new Comparator<Star>() {
            @Override
            public int compare(Star o1, Star o2) {
                if(o1.weight > o2.weight) return 1;
                else return -1;
            }
        });
        makeSet();
        double ans = 0;
        int cnt = 0;
        for (Star x : stars) {
            if (union(x.from, x.to)) {
                ans += x.weight;
                if(++cnt == N-1) break;
            }
        }
        System.out.println(ans);
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    private static int findSet(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]);
    }

    private static void makeSet() {
        parents = new int[N];
        Arrays.fill(parents, -1);
    }
    private static double getWeight(int a, int b) {
        double x = Math.pow(Math.abs(arr[a][0] - arr[b][0]),2);
        double y = Math.pow(Math.abs(arr[a][1] - arr[b][1]),2);
        return Math.sqrt(x+y);
    }
}

class Star {
    int from, to;
    double weight;

    public Star(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
