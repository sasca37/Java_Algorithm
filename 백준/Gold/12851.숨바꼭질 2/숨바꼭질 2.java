import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    static Queue<MyeoungJu> queue = new LinkedList<>();
    static boolean[] visited = new boolean[100001];
    static int answer = Integer.MAX_VALUE;
    static int first = -1;
    static int sameAnswer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        queue.offer(new MyeoungJu(N,K,0));
        bfs();
        System.out.println(answer);
        System.out.println(sameAnswer);
    }
    private static void bfs() {
        while(!queue.isEmpty()) {
            MyeoungJu pos = queue.poll();
            // 시작하는 부분 방문 !!!
            visited[pos.start] = true;
            if(pos.start == pos.end) {
                if (answer == Integer.MAX_VALUE) {
                    first = pos.step;
                }
                answer = Math.min(answer, pos.step);

                if(first == pos.step) sameAnswer++;
//                return;
            }
            else {
                int temp;
                for (int i = 0; i < 3; i++) {
                    if (i==0) {
                        temp = pos.start+1;
                    }
                    else if (i==1) {
                        temp = pos.start-1;
                    }
                    else {
                        temp = pos.start * 2;
                    }
                    if (temp >=0 && temp <= 100000 && !visited[temp]) {
                        queue.offer(new MyeoungJu(temp, pos.end, pos.step+1));
                    }
                }
            }
        }
    }
}

class MyeoungJu {
    int start, end, step;

    public MyeoungJu(int start, int end, int step) {
        this.start = start;
        this.end = end;
        this.step = step;
    }
}