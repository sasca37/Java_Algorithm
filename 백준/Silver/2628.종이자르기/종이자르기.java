import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, R,C;
    static int[][] board;
    static ArrayList<Integer> a = new ArrayList<>();
    static ArrayList<Integer> b = new ArrayList<>();
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        board = new int[R+1][C+1];
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int chk = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if(chk == 0) a.add(num);
            else b.add(num);
        }
        a.add(R);
        b.add(C);
        Collections.sort(a);
        Collections.sort(b);
        for (int i = 0; i < a.size(); i++) {
            int ans = 0;
            for (int j = 0; j < b.size(); j++) {
                if(i == 0 && j == 0) ans = a.get(i) * b.get(i);
                else if(i == 0 && j != 0) ans = a.get(i) * (b.get(j)-b.get(j-1));
                else if(i != 0 && j == 0) ans = (a.get(i)-a.get(i-1)) * b.get(j);
                else ans = (a.get(i)-a.get(i-1)) * (b.get(j) - b.get(j-1));
                answer = Math.max(ans, answer);
            }
        }
        System.out.println(answer);
    }
}