import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.next();
        Main main = new Main();
        System.out.println(main.solution(word));
    }

    private int solution(String word) {
        int answer = 0;
        String[] arr = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        for (int i=0; i<word.length(); i++) {
            for (int j=0; j<arr.length; j++) {
                if (word.charAt(i) == arr[j].charAt(0)) {
                     if (i < word.length()-1 && arr[j].length() == 2) {
                         if (word.charAt(i+1) == arr[j].charAt(1)) {
                            i += 1;
                            break;
                        }
                    }
                    else if (i < word.length()-2 && arr[j].length() == 3) {
                        if (word.charAt(i+1) == arr[j].charAt(1) && word.charAt(i+2) == arr[j].charAt(2)) {
                            i += 2;
                            break;
                        }
                    }
                }
            }
            answer++;
        }

        return answer;
    }


}