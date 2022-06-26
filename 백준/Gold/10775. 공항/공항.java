import java.io.*;
import java.util.Arrays;

public class Main {

    static int[] gates;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        gates = new int[G + 1];
        for (int i=1; i<=G; i++) gates[i] = i;
        int P = Integer.parseInt(br.readLine());
        int ans = 0;
        for (int i = 0; i < P; i++) {
            int plane = Integer.parseInt(br.readLine());
            int gate = findSet(plane);

            if (gate != 0) {
                union(gate, gate - 1);
                ans++;
            }
            else break;
        }
        System.out.println(ans);
    }

    public static void union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot != bRoot) {
            gates[aRoot] = bRoot;
        }
    }

    public static int findSet(int a) {
        if (gates[a] == a) return a;
        return gates[a] = findSet(gates[a]);
    }
}