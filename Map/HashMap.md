## HashMap

### Value 정렬

``` java
public char solution(int num, String s) {
    char answer=' ';
    HashMap<Character, Integer> map = new HashMap<>();
    for (char x : s.toCharArray()) map.put(x, map.getOrDefault(x, 0)+1);
    int max = Integer.MIN_VALUE;
    for (char key : map.keySet()) {
        if (map.get(key) > max) {
            max = map.get(key);
            answer = key;
        }
    }
    return answer;
}
```

- 가장 큰 Value를 가진 키를 반환 
- 만약  **Value 별 정렬**이 필요하다면? 

```java
ArrayList<String> arr = new ArrayList<>(hash.keySet());
Collections.sort(arr, (o1,o2) -> (hash.get(o2).compareTo(hash.get(o1))));
```

- keySet을 담을 List 생성 후 Collections.sort로 정렬한다. o2 - o1 : 내림차순 , o1 - o2 : 오름차순 정렬



### Sliding Window

```java
public int solution(String a, String b) {
    int answer = 0;
    HashMap<Character, Integer> map = new HashMap<>();
    HashMap<Character, Integer> map2 = new HashMap<>();
    int length = b.length();
    for (char index : b.toCharArray()) map2.put(index, map2.getOrDefault(index,0)+1);
    for (int i=0; i<length-1; i++) {
        map.put(a.charAt(i), map.getOrDefault(a.charAt(i), 0) + 1);
    }
    int lt = 0;
    for (int rt=length-1; rt<a.length(); rt++) {
        map.put(a.charAt(rt), map.getOrDefault(a.charAt(rt), 0) +1);
        if (map.equals(map2)) answer++;
        map.put(a.charAt(lt), map.getOrDefault(a.charAt(lt), 0)-1);
        if (map.get(a.charAt(lt)) == 0) map.remove(a.charAt(lt));
        lt++;
    }
    return answer;
}
```

- 아나그램 찾기 위한 알고리즘으로 한 칸씩 밀려가며 값을 비교하는 방식 
- map 간의 같은 지 여부를 equals로 확인할 수 있다. (**Key, Value가 모두 같아야 한다.**)



### Exception

```java
for (int i = 0; i < n - k+1; i++) {
    for (int j =i; j<i+k; j++) {
        map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);
    }
    list.add(map.size());
    for (int index : map.keySet()) map.remove(index);
}
```

- Exception in thread "main" java.util.ConcurrentModificationException 발생 
  - 왜? 여러 개를 삭제하는 과정에서 데이터의 조작이 발생하면 Exception이 터진다.
  - iterator 도 마찬가지로 해당 오류를 해결하지 못한다.

 

```java
for (int i =0; i<k-1; i++) {
    map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
}
int lt=0;
for (int rt=k-1; rt<n; rt++) {
    map.put(arr[rt], map.getOrDefault(arr[rt], 0)+1);
    list.add(map.size());
    map.put(arr[lt], map.get(arr[lt])-1);
    if (map.get(arr[lt]) == 0) map.remove(arr[lt]);
    lt++;
}
```

- 생성 구간을 밖에서 해주고, 삭제 연산은 하나씩 하도록 변경 