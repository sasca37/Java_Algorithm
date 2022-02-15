import java.util.Scanner;

class Main {
    static int[] card;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int[] arr = new int[101];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i=0; i<3; i++) {
            int start = sc.nextInt();
            min = Math.min(min, start);
            int end = sc.nextInt();
            max = Math.max(max, end);
            for (int j=start; j<end; j++) {
                arr[j] +=1;
            }
        }
        int answer = 0;
        for (int i=0; i<arr.length; i++) {
            if (arr[i] ==0) continue;
            else if (arr[i] == 1) answer += a;
            else if (arr[i] == 2) answer += 2 * b;
            else answer += 3 * c;
        }
        System.out.println(answer);
    }


}