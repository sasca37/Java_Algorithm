import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger N = sc.nextBigInteger();
        BigInteger M = sc.nextBigInteger();
        
        BigInteger result = new BigInteger("0");
        BigInteger result2 = new BigInteger("0");
        result = N.divide(M);
        result2 = N.mod(M);
        System.out.println(result);
        System.out.println(result2);
    }
}