import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int arr[], N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int sex = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());
            changeSwitch(sex, index);
        }
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]).append(" ");
            if ((i+1) % 20 == 0) sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void changeSwitch(int s, int index) {
        // 남자
        if (s == 1) {
            for (int i=index-1; i<N; i+=index) {
                arr[i] = (arr[i] == 0)? 1: 0;
            }
        }
        // 여자
        else {
            index--;
            if (index == 0 || index ==N-1) {
                arr[index] = (arr[index] == 0) ? 1 : 0;
                return;
            }
            int lt = index;
            int rt = index;
            while (true) {
                int nextLt = lt-1;
                int nextRt = rt+1;
                if (nextLt >=0 && nextRt < N && arr[nextLt] == arr[nextRt]) {
                    lt--;
                    rt++;
                }
                else break;
            }
            if (lt != rt) {
                for (int i=lt; i<=rt; i++) {
                    arr[i] = (arr[i] == 0)? 1:0;
                }
            }
            else arr[index] = (arr[index] == 0) ? 1 : 0;
        }
    }
}