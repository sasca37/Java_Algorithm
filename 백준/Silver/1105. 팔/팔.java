import java.io.*;
import java.math.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		String strLeft = st.nextToken();
		String strRight = st.nextToken();
		int count = 0;
		
		if(strLeft.length() == strRight.length()) {
			for(int i=0; i<strLeft.length(); i++) {
				if(strLeft.charAt(i) != strRight.charAt(i)) {
					break;
				}
				else {
					if(strLeft.charAt(i) == '8') {
						count++;
					}
				}
			}
		}
		System.out.println(count);
		bw.close();
	}
}