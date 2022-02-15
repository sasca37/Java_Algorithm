import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[] visited = new boolean[250000];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        chkPrime();
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;
            sb.append(isPrime(n)).append("\n");
        }
        System.out.println(sb);
    }
    private static int isPrime(int n) {
        int answer = 0;
        for (int i=n+1; i<=2*n; i++) {
            if (i>=2 && !visited[i]) {
                answer+= 1;
            }
        }
        return answer;
    }
    private static void chkPrime() {
        for (int i = 2; i < visited.length; i++) {
            if(!visited[i]) {
                for (int j=i*2; j< visited.length; j+=i) {
                    visited[j] = true;
                }
            }
        }
    }
}