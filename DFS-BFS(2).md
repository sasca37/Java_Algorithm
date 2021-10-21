## DFS



### 합이 같은 부분 집합

```java
import java.util.*;

public class Main {
    static int n, total =0;
    static String answer ="NO";
    boolean flag = false;

    public void dfs(int level, int sum, int[] arr) {
        if (flag) return;
        if (sum > total / 2) return;
        if (level == n) {
            if ((total-sum)==sum) {
                answer="YES";
                flag = true;
            }
        }
        else {
            dfs(level+1, sum+arr[level], arr);
            dfs(level+1, sum, arr);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        n = 6;
        int[] arr = {1, 3, 5, 6, 7, 10};
        total = Arrays.stream(arr).sum();
        main.dfs(0,0, arr);
        System.out.println(answer);
    }
}
```



## 중복 순열

```java
public class Main {
    static int n,m;
    static int[] pm;
    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        n = 3;
        m = 2;
        pm = new int[m];
        main.dfs(0);
    }

    public void dfs(int level) {
        if(level == m) {
            for (int x : pm) System.out.print(x+ " ");
            System.out.println();
        }
        else {
            for (int i=1; i<=n; i++) {
                pm[level] = i;
                dfs(level+1);
            }
        }
    }
}
```

