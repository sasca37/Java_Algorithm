import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<Score> chkArr = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());
                chkArr.add(new Score(first, second));
            }
            Collections.sort(chkArr, new Comparator<Score>() {
                @Override
                public int compare(Score o1, Score o2) {
                    return o1.first - o2.first;
                }
            });
            int min = chkArr.get(0).second;
            int ans = 1;
            for (int i = 1; i < N; i++) {
                int tmp = chkArr.get(i).second;
                if (tmp < min) {
                    min = tmp;
                    ans++;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static class Score {
        int first, second;

        public Score(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}