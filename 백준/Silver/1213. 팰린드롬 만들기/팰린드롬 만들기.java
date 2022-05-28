import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String word;
    static char[] arr;
    static HashMap<Character, Integer> hashMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        word = br.readLine();
        // 팰린드롬 : 짝수길이면 모든 알파벳이 짝수개 , 홀수길이면 1개만 홀수개
        arr = word.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            hashMap.put(arr[i], hashMap.getOrDefault(arr[i],0)+1);
        }

        ArrayList<Character> list = new ArrayList<>(hashMap.keySet());
        Collections.sort(list);

        int oddCnt = 0;
        for (char x : list) {
            if (hashMap.get(x) % 2 == 1) oddCnt++;
        }
        if (oddCnt > 1 || (oddCnt==1 && word.length() % 2 == 0)) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder ssb = new StringBuilder();
        char finalInput = ' ';
        // 문자 길이가 홀수인 경우
        for (char x : list) {
            int curSize = hashMap.get(x);
            if (curSize % 2 == 1) finalInput = x;
            for (int i = 0; i < curSize / 2; i++) {
                sb.append(x);
                ssb.append(x);
            }
        }

        if (finalInput != ' ') sb.append(finalInput);
        sb.append(ssb.reverse());
        System.out.println(sb);
    }


}
