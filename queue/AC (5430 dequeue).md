[https://www.acmicpc.net/problem/5430](https://www.acmicpc.net/problem/5430)

## 틀린 코드 
```java
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String mission = br.readLine();
            int num = Integer.parseInt(br.readLine());
            String word = br.readLine();
            sb.append(chk(mission, num, word)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    public static String chk(String mission, int num, String word) {
        word = word.replace("[","");
        word = word.replace("]","");
        ArrayList<Integer> arr = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(word,",");
        for (int i = 0; i < num; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < mission.length(); i++) {
            if (mission.charAt(i) == 'R') {
                Collections.reverse(arr);
            }
            else {
                if (arr.isEmpty()) return "error";
                arr.remove(0);
            }
        }
        return arr+"";
    }
}

```
* 시간 초과 발생 배열을 뒤집는 과정에 연산이 많이 발생하는 것 같다.
* Deque를 사용하여 배열을 뒤집지 말고 계산해보자.



## 정답 코드 

```java
import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        ArrayDeque<Integer> deque;
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        while (n--> 0) {
            String mission = br.readLine();
            int num = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), "[],");
            deque = new ArrayDeque<>();
            for (int i = 0; i < num; i++) {
                deque.add(Integer.parseInt(st.nextToken()));
            }
            sb.append(chk(mission, deque)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    public static String chk(String mission, ArrayDeque<Integer> deque) {
        ArrayDeque<Integer> reverseDeque = new ArrayDeque<>();
        boolean turn = false;
        for (int i = 0; i < mission.length(); i++) {
            if (mission.charAt(i) == 'R') {
                turn = !turn;
                continue;
            }
            else {
                if (turn) {
                    if (deque.isEmpty()) return "error";
                    else deque.removeLast();
                }
                else {
                    if(deque.isEmpty()) return "error";
                    else deque.removeFirst();
                }
            }
        }
        if (!turn) return print(deque);
        else {
            while(!deque.isEmpty()) {
                int tmp = deque.pollLast();
                reverseDeque.add(tmp);
            }

            return print(reverseDeque);
        }
    }

    public static String print(ArrayDeque<Integer> deque) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if(!deque.isEmpty()) sb.append(deque.poll());
        while (!deque.isEmpty()) {
            sb.append(',').append(deque.poll());
        }
        sb.append(']');
        return sb.toString();
    }
}
```

- 배열 사이에 공백이 있으면 안된다. (문제를 잘 보자...) 
- R을 입력받았을 때 배열을 돌리지 않고 boolean으로 판별 후 앞 또는 뒤 원소 제거 