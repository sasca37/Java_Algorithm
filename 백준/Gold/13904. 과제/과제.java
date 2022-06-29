import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int maxDay = 0;
        List<Homework> works = new ArrayList<>();
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            maxDay = Math.max(day, maxDay);
            int score = Integer.parseInt(st.nextToken());
            works.add(new Homework(day, score));
        }

        int ans = 0;
        for (int i = maxDay; i > 0; i--) {
            int idx = -1;
            int result = 0;
            for (int j = 0; j < works.size(); j++) {
                int day = works.get(j).day;
                int score = works.get(j).score;
                if (day >= i && result < score) {
                    idx = j;
                    result = score;
                }
            }
            if (idx == -1) continue;
            ans += result;
            works.remove(idx);
        }
        System.out.println(ans);
    }

    static class Homework {
        int day, score;

        public Homework(int day, int score) {
            this.day = day;
            this.score = score;
        }
    }

}