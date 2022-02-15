import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static ArrayList<Integer> primeNumber = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            arr.add(sc.nextInt());
        }
        int max = Collections.max(arr) * 2;
        int[] prime = new int[max+1];
        isPrime(prime);
        int cnt;
        for (int i = 0; i < t; i++) {
            cnt = 0;
            if (prime[arr.get(i)] == 0) System.out.println(0);
            else {
                while (arr.get(i) >= primeNumber.get(cnt)) {
                    cnt++;
                }
                System.out.println(primeNumber.get(cnt)- primeNumber.get(cnt-1));
            }
        }
    }

    public static void isPrime(int[] prime) {
        prime[0] = prime[1] = 1;
        for (int i = 2; i < prime.length; i++) {
            if (prime[i] == 0) {
                primeNumber.add(i);
                for (int j=i*2; j< prime.length; j+=i){
                    prime[j] = 1;
                }
            }
        }
    }
}
