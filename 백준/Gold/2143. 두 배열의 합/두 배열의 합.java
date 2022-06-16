import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());
        int[] B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) B[i] = Integer.parseInt(st.nextToken());

        List<Long> aList = new ArrayList<>();
        List<Long> bList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            long total = 0;
            for (int j = i; j < N; j++) {
                total += A[j];
                aList.add(total);
            }
        }

        for (int i = 0; i < M; i++) {
            long total = 0;
            for (int j = i; j < M; j++) {
                total += B[j];
                bList.add(total);
            }
        }

        Collections.sort(aList);
        Collections.sort(bList);

        int left = 0;
        int right = bList.size()-1;

        long answer = 0;
        while (left < aList.size() && 0 <= right) {
            long sum = aList.get(left) + bList.get(right);
            if (sum == T) {
                long aCnt = 0, bCnt = 0;
                long aTmp = aList.get(left);
                long bTmp = bList.get(right);
                while (left < aList.size() && aList.get(left) == aTmp) {
                    left++;
                    aCnt++;
                }
                while (right >= 0 && bList.get(right) == bTmp) {
                    right--;
                    bCnt++;
                }
                answer += aCnt * bCnt;
            } else if (sum > T) {
                right--;
            } else left++;
        }

        System.out.println(answer);
    }
}