import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // A : 300  , B: 60, C: 10
        int aCnt = 0, bCnt=0, cCnt = 0;
        while(N > 0) {
            if (N >= 300) {
                aCnt += N/300;
                N %= 300;
            } else if (N >= 60) {
                bCnt += N/60;
                N %= 60;
            } else if (N >= 10) {
                cCnt += N / 10;
                N %= 10;
            } else {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(aCnt + " " + bCnt +" " + cCnt);
    }
}