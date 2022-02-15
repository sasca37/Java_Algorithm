import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String word = br.readLine();
            if (word.equals(".")) break;
            int[] pi = getPi(word);
            // 비교할 인덱스
            int indexCnt = word.length() - pi[word.length()-1];
            // 비교할 인덱스랑 나누어 떨어지지 않는다면 패턴이 없으므로 1
            if (word.length() % indexCnt != 0) sb.append("1").append("\n");
            // 패턴 만큼 전체 길이에서 나눈다.
            else sb.append(word.length() / indexCnt).append("\n");
        }
        System.out.println(sb);
    }

    private static int[] getPi(String word) {
        int[] pi = new int[word.length()];
        int j = 0;
        for (int i=1; i<pi.length; i++) {
            // 직전에 일치한 인덱스가 있었으나, 그 다음에 인덱스가 다를 경우 직전 실패함수 값으로 돌아감
            while (j > 0 && word.charAt(i) != word.charAt(j)) {
                j = pi[j-1];
            }
            // 비교하는 인덱스가 같다면 pi에 j를 증가시켜 집어넣는다.
            if (word.charAt(i) == word.charAt(j)) pi[i] = ++j;
        }
        return pi;
    }
}