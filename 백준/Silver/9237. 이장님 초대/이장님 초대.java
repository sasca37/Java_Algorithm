import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] times = new Integer[N + 2];
        times[0] = times[1] = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 2; i < N + 2; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(times, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, times[i] + i+1);
        }
        System.out.println(max+1);
    }

}