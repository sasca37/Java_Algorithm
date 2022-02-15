import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    static Stack<Point> stack = new Stack<>();
    static HashSet<Character> hashSet = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            char alpha = st.nextToken().charAt(0);
            stack.push(new Point(d,alpha));
        }

        char[] arr = new char[N];
        Arrays.fill(arr,'?');
        int nextIndex = -1;
        while (!stack.isEmpty()) {
            Point tmp = stack.pop();
            if(arr[0] == '?') {
                arr[0] = tmp.alphabet;
                nextIndex = tmp.distance % N;
                continue;
            }
            // 이미 다른 원소가있을 떄 같은 원소가 아니라면 !
            if(arr[nextIndex] != '?') {
                if (arr[nextIndex] != tmp.alphabet) {
                    System.out.println("!");
                    return;
                }
                // 같은 원소라면 한바퀴 돌린 상황
                else {
                    nextIndex += tmp.distance;
                    nextIndex %= N;
                }
            }
            // 비어있다면
            else {
                if(hashSet.contains(tmp.alphabet)) {
                    System.out.println("!");
                    return;
                }
                arr[nextIndex] = tmp.alphabet;
                hashSet.add(tmp.alphabet);
                nextIndex += tmp.distance;
                nextIndex %= N;
            }
        }
        for (char x : arr) sb.append(x);
        System.out.println(sb);
    }
}

class Point {
    int distance;
    char alphabet;

    public Point(int distance, char alphabet) {
        this.distance = distance;
        this.alphabet = alphabet;
    }
}