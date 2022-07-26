import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int ans = 0;

        while(true){ 
            int count = 0;
            int num = n;
            while(num!=0){
                if(num%2==1){
                    count+=1;
                }
                num = num/2;
            }
            // 물통의 개수 count

            if(count<=k){ // k개를 넘지않는 비어있지 않는 물병을 만들려고 한다. 최소를 위해서
                break;
            }
            ans +=1; // 갯수가 안맞으니
            n +=1; // 매순간 최선이 바로 하나구입하는것이라서?
            
        }

        System.out.println(ans);

    }
}