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
[출력]
1 1
1 2
1 3
2 1
2 2
2 3
3 1
3 2
3 3
```



## 조합 수 (메모이제이션)

```java
public class Main {
    static int[][] distance;
    static int answer;
    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        int n = 33;
        int r = 19;
        distance = new int[n+1][r+1];
        System.out.println(main.dfs(n,r));
    }

    public int dfs(int n, int r) {
        if (distance[n][r] != 0) return distance[n][r];
        else if (n == r || r== 0) return 1;
        else {
            return distance[n][r] = dfs(n-1,r-1) + dfs(n-1,r);
        }
    }
}
[입력] 5 3 -> 5C3
[출력] 10
```

- nCr = n-1Cr-1 + n-1Cr 을 계산하는 문제 
- distance 배열을 이용해 메모이제이션 적용 



## 수열 추측 

```java
import java.util.*;

public class Main {
    static int[] b, p, ch;
    static int n,f;
    boolean flag = false;
    int[][] dy = new int[35][35];

    public int calculate(int n, int r) {
        if (dy[n][r] > 0) return dy[n][r];
        if (n == r || r ==0) return 1;
        else return dy[n][r] = calculate(n-1, r-1) + calculate(n-1, r);
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        n = 4;
        f = 16;
        b = new int[n];
        p = new int[n];
        ch = new int[n+1];
        for (int i=0; i<n; i++) {
            b[i] = main.calculate(n-1,i);
        }
        main.dfs(0,0);
    }

    public void dfs(int level, int sum) {
        if (flag) return;
        if (level == n) {
            if (sum == f) {
                for (int index : p) System.out.print(index+" ");
                flag = true;
            }
        }
        else {
            for (int i=1; i<=n; i++) {
                if (ch[i] == 0) {
                    ch[i] = 1;
                    p[level] = i;
                    dfs(level+1, sum+(p[level] * b[level]));
                    ch[i] = 0;
                }
            }
        }
    }
}
```

- 파스칼 삼각형 문제 



## 조합 

```java
public class Main {
    static int[] combi;
    static int n, m;
    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        n = 4;
        m = 2;
        combi = new int[m];
        main.dfs(0, 1);
    }

    public void dfs(int level, int start) {
        if (level == m) {
            for (int x : combi) System.out.print(x + " ");
            System.out.println();
        }
        else {
            for (int i=start; i<=n; i++) {
                combi[level] = i;
                dfs(level+1, i+1);
            }
        }
    }
}
[입력] 4 2
[출력]
1 2 
1 3 
1 4 
2 3 
2 4 
3 4 
```

