import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr = new int[1000];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        isDuplicateNum();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String pattern = st.nextToken();
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());
            chkNum(pattern, strike, ball);
        }
        System.out.println(getCnt());
    }

    private static int getCnt() {
        int ans = 0;
        for (int k = 111; k <= 999; k++) {
            if (arr[k] == 1) ans++;
        }
        return ans;
    }

    private static void isDuplicateNum() {
        for (int i = 111; i <= 999; i++) {
            String word = i+"";
            if (word.charAt(1) == '0' || word.charAt(2) == '0') {
                arr[i] = -1;
                continue;
            }
            else if (word.charAt(0) == word.charAt(1) || word.charAt(0) == word.charAt(2) || word.charAt(1) == word.charAt(2)) {
                arr[i] = -1;
                continue;
            }
        }
    }

    private static void chkNum(String pattern, int s, int b) {
        for (int k = 111; k <= 999; k++) {
            if(arr[k] == -1) continue;
            String current = k+"";
            int sCnt = 0;
            int bCnt = 0;
            for (int i = 0; i < 3; i++) {
                if (current.charAt(i) == pattern.charAt(i)) {
                    sCnt ++;
                    continue;
                }
                for (int j = 0; j < 3; j++) {
                    if (current.charAt(i) == pattern.charAt(j)) bCnt ++;
                }
            }
            if(sCnt == s && bCnt == b) arr[k] = 1;
            else arr[k] = -1;
        }
    }
}