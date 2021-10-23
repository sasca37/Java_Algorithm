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



### 중복 순열

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



### 조합 수 (메모이제이션)

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



### 수열 추측 

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



### 조합 

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



### 미로 탐색

```java
public class Main {
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = 0;
    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        board = new int[8][8];
        for (int i=1; i<=7; i++) {
            for (int j=1; j<=7; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        board[1][1] = 1;
        main.dfs(1,1);
        System.out.println(answer);
    }

    public void dfs(int x, int y) {
        if (x == 7 && y == 7) {
            answer++;
        }
        else {
            for (int i = 0; i < 4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                if (nx >= 1  && nx <=7 && ny >=1 && ny <=7 && board[nx][ny] == 0) {
                    board[nx][ny] = 1;
                    dfs(nx,ny);
                    board[nx][ny] = 0;
                }
            }
        }
    }
}
[입력]
0 0 0 0 0 0 0
0 1 1 1 1 1 0
0 0 0 1 0 0 0
1 1 0 1 0 1 1
1 1 0 0 0 0 1
1 1 0 1 1 0 0
1 0 0 0 0 0 0
[출력] 8
```

- 상하좌우 dx, dy 생성 후 dfs 실행 후 벽을 다시 없애주는게 포인트 



### 섬 구하기

```java
import java.util.*;

class Main {
    static int answer = 0, n;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        main.chk(board);
        System.out.println(Arrays.deepToString(board));
        System.out.println(answer);
    }

    public void chk(int[][] board) {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if(board[i][j] == 1) {
                    answer++;
                    dfs(board, i,j);
                }
            }
        }
    }

    public void dfs(int[][] board, int x, int y) {
        board[x][y] = 0;
        for (int i=0; i<8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx>=0 && nx <n && ny >=0 && ny <n && board[nx][ny] == 1) {
                dfs(board, nx,ny);
            }
        }
    }
}
[입력]
7
1 1 0 0 0 1 0
0 1 1 0 1 1 0
0 1 0 0 0 0 0
0 0 0 1 0 1 1
1 1 0 1 1 0 0
1 0 0 0 1 0 0
1 0 1 0 1 0 0
[출력] 5
```

- 상하좌우, 대각선 까지 연결된 1의 묶음을 섬으로 보고 섬의 개수 구하는 문제 



### 피자 배달 거리

```java
import java.util.*;

class Point {
    public int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Main {
    static int n,m, len, answer =Integer.MAX_VALUE;
    static int[] combi;
    static ArrayList<Point> pz, hs;
    public void dfs(int level, int start){
        if(level == m) {
            int sum = 0;
            for (Point h : hs) {
                int dis = Integer.MAX_VALUE;
                for (int i : combi) {
                    dis = Math.min(dis, Math.abs(h.x - pz.get(i).x) + Math.abs(h.y - pz.get(i).y));
                }
                sum += dis;
            }
            answer = Math.min(answer, sum);
        }
        else {
            for (int i=start; i < len; i++) {
                combi[level] = i;
                dfs(level+1, i+1);
            }
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        pz = new ArrayList<>();
        hs = new ArrayList<>();
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                int tmp = sc.nextInt();
                if (tmp == 1) hs.add(new Point(i,j));
                else if (tmp == 2) pz.add(new Point(i,j));
            }
        }
        len = pz.size();
        combi = new int[m];
        main.dfs(0,0);
        System.out.println(answer);
    }

}
```





## BFS



### 미로 최단 거리

```java
class Point {
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Main {
    static int[][] board, dis;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        board = new int[8][8];
        dis = new int[8][8];
        for (int i=1; i<=7; i++) {
            for (int j=1; j<=7; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        main.bfs(1,1);
        if (dis[7][7] == 0) System.out.println(-1);
        else System.out.println(dis[7][7]);
       
    }

    public void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        board[x][y] = 1;
        while(!queue.isEmpty()) {
            Point tmp = queue.poll();
            for (int i=0; i<4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx >=1 && nx <=7 && ny>=1 && ny <= 7 && board[nx][ny] == 0) {
                    board[nx][ny] = 1;
                    queue.offer(new Point(nx, ny));
                    dis[nx][ny] = dis[tmp.x][tmp.y] + 1;
                }
            }
        }
    }
}
[입력]
0 0 0 0 0 0 0
0 1 1 1 1 1 0
0 0 0 1 0 0 0
1 1 0 1 0 1 1
1 1 0 1 0 0 0
1 0 0 0 1 0 0
1 0 1 0 0 0 0
[출력] 12
```

- n x n 크기의 미로와 동일한 거리 배열 생성 후 제일 먼저 값이 들어간 값이 최단거리로 측정 



### 토마토

```java
import java.util.*;

class Point {
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int[][] board, dis;
    static int n,m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Point> queue = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        m = sc.nextInt();
        n = sc.nextInt();
        board = new int[n][m];
        dis = new int[n][m];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                board[i][j] = sc.nextInt();
                if(board[i][j] == 1) queue.offer(new Point(i,j));
            }
        }
        main.bfs();
        boolean flag = true;
        int answer = Integer.MIN_VALUE;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (board[i][j] == 0) flag = false;
            }
        }
        if (flag) {
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    answer = Math.max(answer, dis[i][j]);
                }
            }
        }
        else answer = -1;
        System.out.println(answer);
    }

    public void bfs() {
        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            for (int i=0; i<4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx>=0 && nx <n && ny>=0 && ny <m && board[nx][ny] == 0) {
                    board[nx][ny] = 1;
                    queue.offer(new Point(nx,ny));
                    dis[nx][ny] = dis[tmp.x][tmp.y]+1;
                }
            }
        }
    }
}
[입력]
6 4
0 0 -1 0 0 0
0 0 1 0 -1 0
0 0 -1 0 0 0
0 0 0 0 -1 1
[출력] 4
```

- 익은 토마토 1, 안익은 토마토 0, 없는 자리 -1 이며, 익은 토마토 주변 상하좌우가 하루에 1개씩 익는 최단 거리 문제 

### 섬 구하기

```java
import java.util.*;

class Point {
    public int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Main {
    static int answer = 0, n;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static Queue<Point> queue = new LinkedList<>();

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        main.chk(board);
        System.out.println(Arrays.deepToString(board));
        System.out.println(answer);
    }

    public void chk(int[][] board) {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if(board[i][j] == 1) {
                    answer++;
                    queue.offer(new Point(i,j));
                    bfs(board);
                }
            }
        }
    }

    public void bfs(int[][] board) {
        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            for (int i=0; i<8; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx >=0 && nx <n && ny>=0 && ny <n && board[nx][ny] == 1) {
                    board[nx][ny] = 0;
                    queue.offer(new Point(nx,ny));
                }
            }
        }
    }
}
```

- DFS 에 있는 섬 구하기와 동일한 문제 BFS 버전 
