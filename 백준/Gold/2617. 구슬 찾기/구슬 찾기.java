import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, half, current;
    static boolean[] hVisited, lVisited, answer;
    static int[] lAnswer, hAnswer;
    static ArrayList<ArrayList<Integer>> heavyGraph = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> lightGraph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        half = N / 2 + 1;
        hVisited = new boolean[N + 1];
        lVisited = new boolean[N + 1];
        answer = new boolean[N+1];
        for (int i = 0; i <= N; i++) {
            heavyGraph.add(new ArrayList<>());
            lightGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int heavy = Integer.parseInt(st.nextToken());
            int light = Integer.parseInt(st.nextToken());
            heavyGraph.get(light).add(heavy);
            lightGraph.get(heavy).add(light);
        }
        for (int i = 1; i <= N; i++) {
            lAnswer = new int[N+1];
            hAnswer = new int[N+1];
            hVisited = new boolean[N + 1];
            lVisited = new boolean[N + 1];

            hVisited[i] = true;
            lVisited[i] = true;
            current = i;
            heavyDfs(i);
            lightDfs(i);
        }
        int ans = 0 ;
        for (boolean x : answer) if (x) ans++;
        System.out.println(ans);
    }

    private static void heavyDfs(int num) {
        if (answer[current]) return;
        if (hAnswer[current] >= half) {
            answer[current] = true;
            return;
        }

        for (int next : heavyGraph.get(num)) {
            if (!hVisited[next]) {
                hVisited[next] = true;
                hAnswer[current]++;
                heavyDfs(next);
            }
        }
    }

    private static void lightDfs(int num) {
        if (answer[current]) return;
        if (lAnswer[current] >= half) {
            answer[current] = true;
            return;
        }

        for (int next : lightGraph.get(num)) {
            if (!lVisited[next]) {
                lVisited[next] = true;
                lAnswer[current]++;
                lightDfs(next);
            }
        }
    }
}

