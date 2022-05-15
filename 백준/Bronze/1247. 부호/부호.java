import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int n = Integer.parseInt(br.readLine());
            BigInteger num = new BigInteger("0");
            for (int j = 0; j < n; j++) {
                num = num.add(new BigInteger(br.readLine()));
            }
            if (num.compareTo(BigInteger.ZERO) == -1) sb.append("-").append("\n");
            else if (num.compareTo(BigInteger.ZERO) == 1) sb.append("+").append("\n");
            else sb.append("0").append("\n");
        }
        System.out.println(sb);

    }
}