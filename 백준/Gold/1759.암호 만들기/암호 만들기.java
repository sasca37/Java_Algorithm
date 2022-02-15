import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<String> arr = new ArrayList<>();
    static ArrayList<Character> word = new ArrayList<>();
    static int L, C;
    public static void main(String[] args) throws IOException {
        // 최소 1개의 모음, 2개의 자음
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<C; i++) {
            word.add(st.nextToken().charAt(0));
        }
        Collections.sort(word);
        boolean[] visited = new boolean[C];
        combination(visited, 0,L);
        for (String x : arr) System.out.println(x);
    }
    private static void combination(boolean[] visited,int start, int r) {
        if (r == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<C; i++) {
                if(visited[i]) {
                    sb.append(word.get(i));
                }
            }
            if(chkWord(sb.toString())) {
                arr.add(sb.toString());
            }
        }
        else {
            for (int i=start; i<C; i++) {
                visited[i] = true;
                combination(visited, i+1, r-1);
                visited[i] = false;
            }
        }
    }

    private static boolean chkWord(String pos) {
        int vowels = 0; // 모음
        int consonants = 0; //자음
        for (char tmp : pos.toCharArray()) {
            if (tmp == 'a' || tmp == 'e' || tmp == 'i' || tmp == 'o' || tmp =='u') {
                vowels++;
            }
            else consonants++;
        }
        if (vowels >=1 && consonants >=2) return true;
        else return false;
    }
}
