import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int cnt = 0;
        boolean[] visited = new boolean[N+1];
        for (int i = 2; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                cnt ++;
                if (cnt == K) {
                    System.out.println(i);
                    return;
                }
                for (int j= i*2; j <=N; j+=i) {
                    if(visited[j]) continue;
                    visited[j] = true;
                    cnt++;
                    if (cnt == K) {
                        System.out.println(j);
                        return;
                    }
                }
            }
        }
    }
}