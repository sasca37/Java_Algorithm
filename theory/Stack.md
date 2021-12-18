## Stack

- LIFO 구조 - 후입선출 , 대표적으로 괄호 문제가 존재 

```JAVA
public String solution(String word) {
    String answer = "YES";
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < word.length(); i++) {
        if (word.charAt(i) == '(') {
            stack.push(word.charAt(i));
        }
        else {
            if (stack.isEmpty()) return "NO";
            stack.pop();
        }
    }
    if (!stack.isEmpty()) return "NO";
    return answer;
}
```



### Postfix 후위식

- 후위식이란 3x(5+2)-9 라는 중위식을 우선 순위를 두어 연산자를 두 숫자 뒤에 옮기는 것을 말한다. 해당 식의 후위식 결과는 352+x9-가 된다.

- postfix를 하기전에 char to int에 대해 복습하자. '0' : 은 아스키코드로 48이다. 문자형 '5'는 아스키코드로 53이며, 이를 int형으로 바꾸기 위해선 '5' - '0' 또는 '5' - 48 의 형 변환이 필요하다.  

```java
public int solution(String word) {
    int answer = 0;
    Stack<Integer> stack = new Stack<>();
    for (char index : word.toCharArray()) {
        if (Character.isDigit(index)) {
            stack.push(index-48);
        }
        else {
            int rt = stack.pop();
            int lt = stack.pop();
            if (index == '+') stack.push(lt+rt);
            else if (index == '-') stack.push(lt-rt);
            else if (index == '*') stack.push(lt * rt);
            else if (index == '/') stack.push(lt / rt);
        }
    }
    answer = stack.get(0);
    return answer;
}
```

- 숫자는 push하다가 문자 발견시 두 숫자를 pop한 후 경우에 맞춰 연산 해주면 간단하게 해결할 수 있다. 
- Stack은 List의 자식 중 하나로 get 사용이 가능하다. 



## Queue

- FIFO 구조 - 선입선출
- offer()  : 데이터 삽입 , poll() : 꺼내고 삭제, contains(x) : boolean 반환  

```java
public int solution(int n, int k) {
    int answer = 0;
    int cnt = 0;
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < n; i++) {
        queue.offer(i+1);
    }
    while (queue.size() > 1) {
        if (cnt == k-1) {
            int t =queue.poll();
            cnt=0;
        }
        else {
            int index = queue.poll();
            queue.offer(index);
            cnt++;
        }
    }
    answer = queue.poll();
    return answer;
}
```

- poll() 로 맨앞의 인덱스를 꺼내고 offer로 맨 뒤에 붙이는 예제 (공주 구하기)



### 객체형

- 순서와 같은 추가적인 값이 필요할 경우 class 객체를 통해 선언

```java
class Person {
    int id;
    int priority;
    public Person(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }
    
}
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int n = 5;
        int m = 2;
        int[] arr = {60, 50, 70, 80, 90};
        System.out.println(main.solution(n,m,arr));
    }

    public int solution(int n, int m, int[] arr) {
        int answer = 0;
        Queue<Person> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.offer(new Person(i, arr[i]));
        }
        while (!queue.isEmpty()) {
            Person tmp = queue.poll();
            for (Person x : queue) {
                if (x.priority > tmp.priority) {
                    queue.add(tmp);
                    tmp = null;
                    break;
                }
            }
            if (tmp != null) {
                if (tmp.id==m) return answer;
                else answer++;
            }
        }
        System.out.println(queue);
        return answer;
    }
}
```



### 객체형 순위 

```java
class Solution {
    public static void main(String[] args) {
        int[] priorities = {1,1,9,1,1,1};
        int location = 0;
        Solution main = new Solution();
        System.out.println(main.solution(priorities, location));
    }
    public int solution(int[] priorities, int location) {
        Queue<Pair> queue = new LinkedList<>();
        int answer = 0;

        for (int i = 0; i < priorities.length; i++) {
            queue.add(new Pair(i, priorities[i]));
        }

        while (!queue.isEmpty()) {

            int current = queue.peek().value;

            boolean flag = false;

            for (Pair pair : queue) {
                if (pair.value > current) {
                    // System.out.println(pair.value);
                    flag = true;
                    break;
                }
            }

            if (flag) {
                Pair temp = queue.poll();
                queue.add(temp);
            }
            else {
                answer++;
                Pair pair = queue.poll();

                if (pair.index == location) {
                    return answer;
                }
            }
        }
        return answer;
    }

    class Pair {
        int index;
        int value;

        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
```

