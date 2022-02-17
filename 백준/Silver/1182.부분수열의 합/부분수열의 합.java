import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, input[], S;
    static boolean[] isSelected;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 집합의 크기
        S = Integer.parseInt(st.nextToken()); // 목표 합

        input = new int[N];
        isSelected = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        generateSubset(0);
        System.out.println(answer);
    }

    public static void generateSubset(int cnt) { // 부분집합에 고려해야 하는 원소의 개

        if (cnt == N) {
            boolean flag = false;
            int sum = 0;
            for (int i=0; i<N; i++) {
                if(isSelected[i]) {
                    flag = true;
                    sum+= input[i];
                }
            }
            if (sum == S && flag) answer++;
            return;
        }

        // 현재 원소를 선택
        isSelected[cnt] = true;
        generateSubset(cnt+1);
        // 현재 원소를 비선택
        isSelected[cnt] = false;
        generateSubset(cnt+1);
    }
}