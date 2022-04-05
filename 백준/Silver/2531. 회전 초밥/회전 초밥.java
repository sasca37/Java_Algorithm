import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, D, K, C, belts[], sushis[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        belts = new int[N]; // 벨트 사이즈 : N
        sushis = new int[D + 1]; // 스시 가짓수 : D
        for (int i = 0; i < N; i++) belts[i] = Integer.parseInt(br.readLine());

        int cnt = 0;
        int ans = 0;
        int lt = 0;
        int rt = K-1;

        for (int i=lt; i<=rt; i++) {
            if(sushis[belts[i]] == 0) cnt++;
            sushis[belts[i]]++;
        }

        if (sushis[C] == 0) ans = cnt+1;
        else ans = cnt;

        while (lt < N) {
            lt++;
            rt++;
            if(--sushis[belts[lt-1]] == 0) cnt--;
            if(++sushis[belts[rt % N]] == 1) cnt++;

            if(cnt >= ans) {
                if (sushis[C] == 0) ans = cnt+1;
                else ans = cnt;
            }
        }
        System.out.println(ans);
    }
}