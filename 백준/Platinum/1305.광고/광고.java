import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String text = br.readLine();
        int[] pi = getPi(text);
        System.out.println(N-pi[N-1]);
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