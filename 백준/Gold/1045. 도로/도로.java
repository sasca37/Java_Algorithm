import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans[],parents[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = new int[N];
        makeSet();
        int cnt = 0;
        int subCnt = M-N+1;
        for (int i = 0; i < N; i++) {
            char[] word = br.readLine().toCharArray();
            for (int j = i + 1; j < N; j++) {
                if(word[j] == 'Y') {
                    if(union(i,j)) {
                        ans[i]++;
                        ans[j]++;
                        ++cnt;
                    }
                    else if(subCnt > 0) {
                        ans[i]++;
                        ans[j]++;
                        subCnt--;
                    }
                }
            }
        }
        if (cnt < N-1 || subCnt != 0) {
            System.out.println(-1);
            return;
        }

        for (int i=0; i<N; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void makeSet() {
        parents = new int[N];
        for (int i=0; i<N; i++) parents[i] = i;
    }

    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    private static int findSet(int a) {
        if (a == parents[a]) return a;
        return parents[a] = findSet(parents[a]);
    }
}