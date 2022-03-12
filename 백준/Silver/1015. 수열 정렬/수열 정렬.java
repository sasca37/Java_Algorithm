import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] sub = new int[N];
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            sub[i] = tmp;
            arr[i] = tmp;
        }
        Arrays.sort(sub);
        ArrayList<Point> mainArr = new ArrayList<>();
        for (int i=0; i<N; i++) {
            mainArr.add(new Point(i, sub[i]));
        }
        Collections.sort(mainArr);

        for (int i=0; i<N; i++) {
            int current = arr[i];
            for (int j=0; j < mainArr.size(); j++) {
                if (mainArr.get(j).value == current) {
                    sb.append(mainArr.get(j).index +" ");
                    mainArr.remove(mainArr.get(j));
                    j--;
                    break;
                }
            }
        }
        System.out.println(sb);
    }

    static class Point implements Comparable<Point> {
        int index, value;

        public Point(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Point o) {
            return this.value - o.value;
        }
    }
}