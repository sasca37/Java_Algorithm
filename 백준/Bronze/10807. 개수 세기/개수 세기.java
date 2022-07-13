import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cnt = 0;
		int V = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if (tmp == V) cnt++;
		}
		System.out.println(cnt);
	}
	
}
