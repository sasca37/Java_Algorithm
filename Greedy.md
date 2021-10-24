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