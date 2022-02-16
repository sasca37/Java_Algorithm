import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
    (n,m) = ((n-1,m) + m-1) % i + 1;
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int answer = 1;
        for (int i=2; i<=N; i++) {
            answer = (answer+K-1) % i + 1;
        }
        System.out.println(answer);
    }
}