import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<k; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for (int i = 0; i < k; i++) {
            int num = list.get(i);
            if(set.contains(num)) continue;
            if (set.size() < n && set.add(num)) continue;
            int max = -1, idx = -1;

            for (int x : set) {
                int tmp = 0;
                List<Integer> sub = list.subList(i + 1, k);
                if (sub.contains(x)) {
                    tmp = sub.indexOf(x) + 1;
                }
                else {
                    tmp = k - i - 1;
                }
                if (tmp > max) {
                    max = tmp;
                    idx = x;
                }
            }
            set.remove(idx);
            set.add(num);
            ans++;
        }
        System.out.println(ans);
    }
}