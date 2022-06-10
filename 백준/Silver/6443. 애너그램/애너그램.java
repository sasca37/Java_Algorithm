import java.io.*;
import java.util.*;

public class Main {
    static Set<String> arr;
    static char[] words;
    static String word;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            arr = new HashSet<>();
            word = br.readLine();
            words = new char[word.length()];
            visited = new int[26];
            for (char x : word.toCharArray()) visited[x-'a']++;
            dfs(0);
            ArrayList<String> x = new ArrayList<>(arr);
            Collections.sort(x);
            for (String w : x) sb.append(w).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int level) {

        if(level == word.length()) {
            StringBuilder sb = new StringBuilder();
            for (char x : words) sb.append(x);
            arr.add(sb.toString());
            return;
        }

        for (int i = 0; i < 26; i++) {
            if(visited[i] > 0) {
                visited[i]--;
                words[level] = (char) (i +'a');
                dfs(level+1);
                visited[i]++;
            }
        }
    }
}