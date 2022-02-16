import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        long tmp = 0;
        int answer = 0;
        for (int i=1; i<=N; i++) {
            tmp += i;
            answer++;
            if(tmp > N) {
                answer--;
                break;
            } else if (tmp == N) break;
        }
        System.out.println(answer);
    }
}