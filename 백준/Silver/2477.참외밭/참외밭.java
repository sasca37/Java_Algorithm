import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        Point[] points = new Point[18];
        StringTokenizer st;
        int maxLen = 0;
        int maxLenIndex = 0;
        for (int i=0; i<6; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            points[i] = new Point(d, len);
            // 좌 우 인덱스 비교를 위해 한번더 복사
            points[i+6] = new Point(d,len);
            points[i+12] = new Point(d,len);
            maxLen = Math.max(maxLen, len);
        }
        // 가장 긴 인덱스값 구하기
        for (int i=6; i<12; i++) {
            if(points[i].len == maxLen) {
                maxLenIndex = i;
            }
        }

        int secondMaxLen = Math.max(points[maxLenIndex - 1].len, points[maxLenIndex + 1].len);
        int secondMaxLenIndex = points[maxLenIndex-1].len== secondMaxLen? maxLenIndex-1 : maxLenIndex+1;

        // 긴 두개의 인덱스의 좌우의 차이 (뺄 곳의 가로 또는 세로)
        int a = Math.abs(points[maxLenIndex-1].len-points[maxLenIndex+1].len);
        int b = Math.abs(points[secondMaxLenIndex-1].len-points[secondMaxLenIndex+1].len);
        System.out.println((maxLen * secondMaxLen - a*b) * K);
    }
}

class Point {
    int d, len;

    public Point(int d, int len) {
        this.d = d;
        this.len = len;
    }
}