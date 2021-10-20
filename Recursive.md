## Recursive

- 모든 함수는 기본적으로 스택 프레임을 가진다. 
- 스택 안에는 매개변수, 지역변수, 복귀 주소 등을 가지고 있다. 



```java
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        int n = 3;

        main.solution(n);
    }

    public void solution(int n) {
        if (n==0) return;
        else {
            System.out.print(n +" "); // [출력] 3 2 1
            solution(n-1);
            System.out.print(n +" "); // [출력] 1 2 3
        }
    }
}

```

- 스택에 계속 쌓이면서 solution(0) 이 return 하는 순간 스택에 쌓인 부분을 pop()하면서 나머지 작업을 한다. 
- 그렇기 때문에 solution(n-1) 을 선언 하기 전에는 push()하면서 출력이되고 나머지는 pop() 하면서 출력 



### 2진수 

```java
public class Main {
    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        Main main = new Main();
        int n = 11;

        main.solution(n);
    }

    public void solution(int n) {
        if (n == 0) return;
        else {
            solution(n/2);
            System.out.print(n%2+"");
        }
    }
}
[출력] 1011
```

- 2진수는 10진수의 값을 몫이 1이 될 때까지 2로 나눈 후, 역순으로 출력하면 된다.



### Factorial

```java
public int solution(int n) {
    if (n == 1) return 1;
    else return n * solution(n-1);
}
```



### Fibonacci

```java
public class Main {
    static int[] fibo;
    public static void main(String[] args) {
        Main main = new Main();
        int n = 45;
        fibo = new int[n+1];
        main.solution(n);
        for (int i=1; i<=n; i++) System.out.print(fibo[i] +" ");
    }

    public int solution(int n) {
        //if (fibo[n] != 0) return  fibo[n]; 메모이제이션 사용 
        if (n == 1 || n==2)  return fibo[n] = 1;
        else return fibo[n] = solution(n-1) + solution(n-2);
    }
}
[출력]
1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765 10946 17711 28657 46368 75025 121393 196418 317811 514229 832040 1346269 2178309 3524578 5702887 9227465 14930352 24157817 39088169
```

- 피보나치 값을 배열에 담아서 처리, 모든 경우를 처리하기 때문에 시간이 오래걸린다. 
- 메모이제이션을 사용하여 구한 값은 바로 가져올 수 있도록 처리 하자. 
- 재귀함수를 사용하는 것 보다는 for문을 사용하는 것이 시간복잡도가 빠르다.


## DFS

### 이진트리 순회

```java
class Node {
    int data;
    Node lt, rt;

    public Node(int val) {
        data = val;
        lt=rt=null;
    }
}

public class Main {
    Node root;
    public void DFS(Node root) {
        if (root == null) return;
        else {
//            System.out.print(root.data+" "); //전위 순회
            DFS(root.lt);
//            System.out.print(root.data +" "); //중위 순회
            DFS(root.rt);
            System.out.print(root.data +" "); //후위 순회 
        }
    }
    public static void main(String[] args) {
        Main tree = new Main();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(3);
        tree.root.lt.lt = new Node(4);
        tree.root.lt.rt = new Node(5);
        tree.root.rt.lt = new Node(6);
        tree.root.rt.rt = new Node(7);
        tree.DFS(tree.root);
    }
}
```
- 전위순회 : 부모 - 왼쪽 자식 - 오른쪽 자식 순서
- 중위순회 : 왼쪽 자식 - 부모 - 오른쪽 자식 순서
- 후위순회 : 왼쪽 자식 - 오른쪽 자식 - 부모 순서 



### 부분 집합 

```java
public class Main {
    static int[] chk;
    static int n;
    public static void main(String[] args) {
        Main main = new Main();
        n = 3;
        chk = new int[n+1];
        main.dfs(1);
    }

    public void dfs(int depth) {
       if (depth > n) {
           StringBuilder sb = new StringBuilder();
           for (int i=1; i<chk.length; i++) {
               if (chk[i] == 1) sb.append(i+" ");
           }
           System.out.println(sb);
           return;
       }
       else {
           chk[depth] = 1;
           dfs(depth+1);
           chk[depth] = 0;
           dfs(depth+1);
       }
    }
}
```



## BFS

- 넓이 우선 탐색 : 레벨 탐색 
