import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, start, end, answer = -1;
    static boolean[] visited;
    // 양방향 인접리스트 구현
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        // 1 ~ 9 리스트 공간 생성
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        dfs(start, 0);
        System.out.println(answer);
    }

    private static void dfs(int current, int cnt) {
        visited[current] = true;
        for(int x : graph.get(current)) {
            if (!visited[x]) {
                if(x == end) {
                    answer = cnt+1;
                    return;
                }
                dfs(x, cnt+1);
            }
        }
    }
}