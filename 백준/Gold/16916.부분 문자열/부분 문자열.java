import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        String pattern = br.readLine();
        kmp(text, pattern);
    }

    private static void kmp(String t, String p) {
        int[] pi = getPi(p);
        int j = 0;
        for (int i = 0; i < t.length(); i++) {
            while (j > 0 && t.charAt(i) != p.charAt(j)) {
                j = pi[j-1];
            }
            if (t.charAt(i) == p.charAt(j)) {
                if (j == p.length()-1) {
                    System.out.println(1);
                    return;
                }
                else j++;
            }
        }
        System.out.println(0);
    }

    private static int[] getPi(String p) {
        int[] pi = new int[p.length()];
        int j = 0;
        for (int i = 1; i < p.length(); i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) {
                j = pi[j-1];
            }
            if (p.charAt(i) == p.charAt(j)) pi[i] = ++j;
        }
        return pi;
    }
}