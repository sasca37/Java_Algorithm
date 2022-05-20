import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int A,B,C;
    static ArrayList<Integer> answer = new ArrayList<>();
    static ArrayList<Point> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        dfs(0,0,C);
        StringBuilder sb = new StringBuilder();
        Collections.sort(answer);
        for (int x : answer) sb.append(x).append(" ");
        System.out.println(sb);
    }

    private static void dfs(int a, int b, int c) {
        for (Point x : list) {
            if (x.a == a && x.b == b && x.c == c) return;
        }
        list.add(new Point(a,b,c));
        if (a == 0) {
            if(!answer.contains(c)) answer.add(c);
        }

        if (a!= 0) {
            // b 또는 c로 이동
            if (a + b > B) {
                dfs(a + b - B, B, c);
            } else {
                dfs(0, a + b, c);
            }
            if (a + c > C) {
                dfs (a+c - C, b, C);
            }
            else {
                dfs(0, b, a + c);
            }
        }
        if (b != 0) {
            // a 또는 c로 이동
            if (a + b > A) {
                dfs (A, a+b-A, c);
            } else {
                dfs (a+b, 0, c);
            }

            if (b + c > C) {
                dfs (a, b+c-C,C);
            } else {
                dfs(a, 0, b+c);
            }
        }
        if (c != 0) {
            // a 또는 b로 이동
            if (c + a > A) {
                dfs (A, b, a+c - A);
            } else {
                dfs (a+c, b, 0);
            }

            if (c + b > B) {
                dfs (a, B, c+b - B);
            } else {
                dfs (a, b+c, 0);
            }
        }
    }
}

class Point {
    int a, b, c;

    public Point(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}