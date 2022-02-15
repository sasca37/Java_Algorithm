import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long answer = 0;
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("black", 0);
        hashMap.put("brown", 1);
        hashMap.put("red", 2);
        hashMap.put("orange", 3);
        hashMap.put("yellow", 4);
        hashMap.put("green", 5);
        hashMap.put("blue", 6);
        hashMap.put("violet", 7);
        hashMap.put("grey", 8);
        hashMap.put("white", 9);
        answer += hashMap.get(br.readLine())*10;
        answer += hashMap.get(br.readLine());
        int n = (int) Math.pow(10, hashMap.get(br.readLine()));
        answer *= n;
        System.out.println(answer);
    }
}