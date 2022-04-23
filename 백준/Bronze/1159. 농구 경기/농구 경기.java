import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] arr = new char[N];
		String result = "";
		
		for(int i = 0; i < N; i++) {
			arr[i] = (br.readLine()).charAt(0);
		}
		Arrays.sort(arr);
		
		for(int i = 0; i < (N - 1); i++) {
			int count = 0;
			for(int j = (i + 1); j < N; j++) {
				//배열의 값이 같고, 비교 값이 0이 아니면 count에 +1.
				if(arr[i] == arr[j] && arr[i] != '0') {
					count++;
					arr[j] = '0';
				}
			}
			if(count > 3) {
				result += arr[i];
			}
		}
		if(result.equals("")) {
			System.out.println("PREDAJA");
		}else {
			System.out.println(result);
		}
	}

}