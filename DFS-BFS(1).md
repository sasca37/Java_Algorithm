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



### 끝 노드 최단 거리

- 최단 거리 문제는 DFS가 아닌 **BFS로 접근**하는 것이 올바른 판단이다.
- 자식 노드가 1개인 경우 DFS를 사용할 수 없지만 해당 경우는 없다는 가정으로 DFS로 접근해보자.

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

    public int dfs(int level, Node root) {
        if (root.lt == null && root.rt == null) {
            return level;
        }
        else return Math.min(dfs(level+1, root.lt), dfs(level+1, root.rt));
    }

    public static void main(String[] args) {
        Main tree = new Main();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(3);
        tree.root.lt.lt = new Node(4);
        tree.root.lt.rt = new Node(4);
        System.out.println(tree.dfs(0, tree.root));
    }
}
```

- dfs는 **if ~ else 사용**만 기억하자. 

## BFS

- 넓이 우선 탐색 : **레벨 탐색**으로 **최단 거리**를 구하는 알고리즘으로 볼 수 있다. 

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
    public void BFS(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();
            System.out.print(depth +" : ");
            for (int i=0; i<len; i++) {
                Node cur = queue.poll();
                System.out.print(cur.data+" ");
                if(cur.lt != null) queue.offer(cur.lt);
                if(cur.rt != null) queue.offer(cur.rt);
            }
            depth++;
            System.out.println();
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
        tree.BFS(tree.root);
    }
}
[출력]
0 : 1 
1 : 2 3 
2 : 4 5 6 7
```



### 최단 거리 

```java
public class Main {
    HashSet<Integer> chk = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    int[] distance = {1, -1, 5};
    int level = 0;
    private int bfs(int start, int end) {
        queue.offer(start);
        chk.add(start);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i=0; i<len; i++) {
                int x = queue.poll();
                for (int index : distance) {
                    int nx = x+index;
                    if (nx == end) return level+1;
                    if (nx >= 1 && nx <= 10000 && !chk.contains(nx)) {
                        chk.add(nx);
                        queue.offer(nx);
                    }
                }
            }
            level++;
        }
        return 0;
    }
    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        int start = 5;
        int end = 14;
        System.out.println(main.bfs(start,end));
    }
}
```

- start 지점에서 end 지점까지의 최단 거리를 구하는 문제 ( +1, -1, +5로만 이동)



### 끝 노드 최단거리

```java
public class Main {
    Node root;
    public int bfs(Node root) {
        Queue<Node> queue = new LinkedList<>();
        int level = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i=0; i<len; i++) {
                Node cur = queue.poll();
                if (cur.lt == null && cur.rt == null) return level;
                if (cur.lt != null) queue.offer(cur.lt);
                if (cur.rt != null) queue.offer(cur.rt);
            }
            level++;
        }
        return 0;
    }
}
```



### 그래프 최단 거리

```java
import java.util.*;

public class Main {
    static int n, m;
    static int[] answer;
    static int[] chk;
    static ArrayList<ArrayList<Integer>> graph;

    public void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        chk[v] = 1;
        queue.offer(v);
        while (!queue.isEmpty()) {
            int cv = queue.poll();
            for (int nv : graph.get(cv)) {
                if (chk[nv] == 0) { // 방문 X
                    chk[nv] = 1;
                    queue.offer(nv);
                    answer[nv] = answer[cv] + 1; // 지나온 거리 +1
                }
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        graph = new ArrayList<>();
        answer = new int[n+1];
        chk = new int[n+1];
        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
        }
        main.bfs(1);
        System.out.println(Arrays.toString(answer));
    }
}
```



## Graph

- G(V, E)로 표현  (Vertex : 노드, Edge : 간선)

```java
// 양방향(무방향)그래프 표현  
graph[a][b] = 1;
graph[b][a] = 1;
// 방향 그래프 표현
graph[a][b] = 1;
// 가중치 방향 그래프 
graph[a][b] = c; // a에서 b로 가는 가중치는 c
```



### 경로 탐색

```java
public class Main {
    static int n, m, answer = 0;
    static int[][] graph;
    static int[] chk;

    public void dfs(int v) {
        if (v==n) answer++;
        else {
            for (int i = 1; i <= n; i++) {
                if (graph[v][i] == 1 && chk[i] == 0) {
                    chk[i] = 1;
                    dfs(i);
                    chk[i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        graph = new int[n+1][n+1];
        chk = new int[n+1];
        for (int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a][b] = 1;
        }
        chk[1] = 1;
        main.dfs(1);
        System.out.println(answer);
    }
}
```

- 방향그래프 1번에서 n번으로 가는 모든 경로 수 출력 
- 메모리를 많이 사용하기 때문에 비효율적이다. 



### 경로 탐색 - 인접리스트

- 배열 사용이 아닌 List를 이용해 간선이 있는 경우만 담는다.

```java
import java.util.*;

public class Main {
    static int n, m, answer = 0;
    static int[] chk;
    static ArrayList<ArrayList<Integer>> graph;

    public void dfs(int v) {
        if (v==n) answer++;
        else {
            for (int nv : graph.get(v)) {
                if (chk[nv] == 0) {
                    chk[nv] =1;
                    dfs(nv);
                    chk[nv]=0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        graph = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        chk = new int[n+1];
        for (int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
        }
        chk[1] = 1;
        main.dfs(1);
        System.out.println(answer);
    }
}
```



