## 회의실 배정

```java
import java.util.*;

class Time implements Comparable<Time> {
    public int s,e;

    public Time(int s, int e) {
        this.s = s;
        this.e = e;
    }

    @Override
    public int compareTo(Time o) {
        if (this.e == o.e) return this.s - o.s;
        else return this.e - o.e;
    }

}
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        int n = sc.nextInt();
        ArrayList<Time> arr = new ArrayList<>();
        for (int i=0; i<n; i++) {
            arr.add(new Time(sc.nextInt(), sc.nextInt()));
        }
        int answer = main.solution(arr, 0);
        System.out.println(answer);
    }

    public int solution(ArrayList<Time> arr, int answer) {
        Collections.sort(arr);
        int end = Integer.MIN_VALUE;
        for (Time object : arr) {
            if (object.e >= end) {
                answer++;
                end = object.e;
            }
        }
        return answer;
    }
}
```

- Comparable를 통한 객체 정렬 



## 결혼식

```java
import java.util.*;

class Time implements Comparable<Time> {
    public int time;
    public char state;
    public Time(int time, char state) {
        this.time = time;
        this.state = state;
    }
    @Override
    public int compareTo(Time o) {
        if (this.time == o.time) return this.state - o.state;
        else return this.time - o.time;
    }
}

class Main {
    public int solution(ArrayList<Time> arr) {
        int answer = Integer.MIN_VALUE;
        int cnt = 0;
        for (Time ob : arr) {
            if (ob.state == 's') cnt++;
            else cnt--;
            answer = Math.max(answer, cnt);
        }
        return answer;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        int n = sc.nextInt();
        ArrayList<Time> arr = new ArrayList<>();
        for (int i=0; i<n; i++) {
            int sT = sc.nextInt();
            int eT = sc.nextInt();
            arr.add(new Time(sT, 's'));
            arr.add(new Time(eT, 'e'));
        }
        Collections.sort(arr);
        for (Time o : arr) System.out.println(o.state +" " + o.time);
        int answer = main.solution(arr);
        System.out.println(answer);
    }

}
```

- 동 시간대 들어온 사람의 최댓값 구하는 문제 
- 입력 값을 따로 나누어서 분할하는 아이디어가 중요 



## 최대수입스케쥴 (Priority Queue)

```java
import java.util.*;

class Rect implements Comparable<Rect> {
    int money;
    int day;

    public Rect(int money, int day) {
        this.money = money;
        this.day = day;
    }

    @Override
    public int compareTo(Rect o) {
        if (o.day == day) return o.money - money;
        else return o.day - day;
    }
}

class Main {
    static int max = 0;
    static int n=0;

    public int solution(ArrayList<Rect> arr) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int j=0;
        for (int i=max; i>=1; i--) {
            for ( ; j<n; j++) {
                System.out.println(j);
                if (arr.get(j).day < i) break;
                pq.offer(arr.get(j).money);
            }
            if (!pq.isEmpty()) answer+=pq.poll();
        }
        return answer;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        ArrayList<Rect> arr = new ArrayList<>();
        for (int i=0; i<n; i++) {
            arr.add(new Rect(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(arr);
        max = arr.get(0).day;
        System.out.println(main.solution(arr));
    }

}
```



## 다익스트라 (Priority Queue)

```java
import java.util.*;

class Edge implements Comparable<Edge> {
    public int vertex;
    public int weight;

    public Edge(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.vertex - o.vertex;
    }
}


class Main {
    static int[] dis;
    public int solution(ArrayList<ArrayList<Edge>> graph) {
        int answer = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1,0));
        dis[1] = 0;
        while (!pq.isEmpty()) {
            Edge tmp = pq.poll();
            int v = tmp.vertex;
            int w = tmp.weight;
            if (w > dis[v]) continue;
            for (Edge ob : graph.get(v)) {
                if (dis[ob.vertex] > ob.weight + w) {
                    pq.offer(new Edge(ob.vertex, ob.weight+w));
                    dis[ob.vertex] = ob.weight +w;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) graph.add(new ArrayList<>());
        dis = new int[n+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        for (int i=0; i<m; i++) {
            graph.get(sc.nextInt()).add(new Edge(sc.nextInt(), sc.nextInt()));
        }
        main.solution(graph);
        System.out.println(Arrays.toString(dis));
    }
}
```

- Priority Queue를 사용한 다익스트라 알고리즘 (nlogn)
- 큐 안에 들어온 값을 꺼내올 때 시간 복잡도가 크게 줄어든다. 
- dis[] 배열에 출발 정점 값, Edge에 도착 정점, 가중치를 담는게 포인트 



## 친구인가 (Union & Find)

```java
import java.util.*;
class Main {
	static int[] unf;
	public static int Find(int v){
		if(v==unf[v]) return v;
		else return unf[v]=Find(unf[v]);
	}
	public static void Union(int a, int b){
		int fa=Find(a);
		int fb=Find(b);
		if(fa!=fb) unf[fa]=fb;
	}
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		int n=kb.nextInt();
		int m=kb.nextInt();
		unf=new int[n+1];
		for(int i=1; i<=n; i++) unf[i]=i;
		for(int i=1; i<=m; i++){
			int a=kb.nextInt();
			int b=kb.nextInt();
			Union(a, b);
		}
		int a=kb.nextInt();
		int b=kb.nextInt();
		int fa=Find(a);
		int fb=Find(b);
		if(fa==fb) System.out.println("YES");
		else System.out.println("NO");		
	}
}
```

