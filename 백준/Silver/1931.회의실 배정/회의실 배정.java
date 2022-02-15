import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Meeting[] meetings;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        meetings = new Meeting[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings[i] = new Meeting(start, end);
        }
        Arrays.sort(meetings);
        int lastEnd = meetings[0].end;
        answer++;
        for (int i=1; i<N; i++) {
            if (meetings[i].start >= lastEnd) {
                lastEnd = meetings[i].end;
                answer++;
            }
        }
        System.out.println(answer);
    }
}

class Meeting implements Comparable<Meeting>{
    int start, end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
    // end 시간 기준으로 정렬
    @Override
    public int compareTo(Meeting o) {
        if(this.end == o.end) return this.start - this.end;
        else return this.end - o.end;
    }
}