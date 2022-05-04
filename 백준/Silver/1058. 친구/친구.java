import java.io.*;
import java.util.Arrays;
 
class Main {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        int N = Integer.parseInt(br.readLine());
        int i, j, k;
        char[][] arr = new char[N][N]; // 입력을 저장하는 배열
        int[] result = new int[N]; // 각 노드의 친구의 수를 저장
        int[][] mark = new int[N][N]; // 친구로 이미 등록했는지를 체크
        String s;
        for (i = 0; i < N; i++) {
            s = br.readLine();
            for (j = 0; j < N; j++) {
                arr[i][j] = s.charAt(j);
            }
        }
 
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                if (arr[i][j] == 'Y') { // 자기 자신과 친구면
                    if (mark[i][j] == 0) {
                        result[i]++; // 친구 수 +1 해주고,
                        mark[i][j] = 1; // 친구로 체크해준다
                    }
                    for (k = 0; k < N; k++) { // 그리고 친구의 친구를 구하기 위해
                        if (arr[j][k] == 'Y' && mark[i][k] == 0 && i != k) { // 친구의 친구가 존재하고, 아직 체크하지 않았으며, 친구의 친구가 내가 아닐때,
                            result[i]++; // 친구 수 +1
                            mark[i][k] = 1; // 친구로 체크
                        }
                    }
                }
            }
        }
 
        Arrays.sort(result);
        bw.write(String.valueOf(result[N - 1])); // 소팅해서 가장 큰 값 출력
        bw.flush();
    }
}