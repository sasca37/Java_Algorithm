## 객체지향 프로그래밍 - OOP

- 현실 세계를 객체로 모델링하여 소프트웨어를 개발하는 방법 
- 소프트웨어를 개발할 때 시스템에 대한 요구사항이 계속해서 추가되고 변경된다. 이를 객체지향에 대입
  - 장점 : 객체마다 유연하게 프로그래밍하여 시스템의 확장이나 변경에 쉽게 대응(안정적인 유지 보수 가능)  
  - 단점 : 처리 속도가 상대적으로 느리고 설계할 때 많은 시간과 노력 필요 



## 객체지향 프로그래밍의 3요소



### 캡슐화(Encapsulation) - 정보은닉

```java
public class Ex1 {
    private int id;
    private String name;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
```

> **멤버변수는 private, Getter&Setter는 public으로 열어 둔다.**

- 외부에서 내부의 내용을 숨겨 데이터를 보호하고 다른 객체의 접근을 제한하는 것
  - 예 ) 멤버변수를 public으로 하면 외부에서 ex1.name과 같이 값을 가져올 수 있지만 이걸 private으로 막는다.
  - 메서드를 통해 간접적으로 접근하도록 제한 (public으로 메서드를 열어둠)
- 캡슐화를 통해 정보의 은닉이 가능하여 중요한 정보를 가볍게 접근하는 것을 막아 데이터 조작이 일어날 수 없다.

![사진1](https://user-images.githubusercontent.com/81945553/129589399-e13d2e66-47f0-4c73-b9eb-96f6aacc58fb.png)

- 데이터의 값들의 신뢰성을 위해 접근 제어자의 활용 중요 



### 다형성

- 하나의 객체가 여러가지 형태를 가질 수 있는 것
  - 예 ) 핸드폰 객체가 전화, 문자, SNS 등 여러 가지 기능을 수행할 수 있는 것 

```java
public class Ex2 {

    public void method(){
        System.out.println("a");
    }
}

class Sub extends Ex2 {

    @Override
    public void method() {
        System.out.println("b");
    }
}
```

- Overriding(오버라이딩): 부모로 부터 상속 받은 메서드를 자식 객체에서 재정의 하는것

```java
public class Ex3 {
    public void print() {
        System.out.println("오버로딩1");
    }

    String print(Integer a) {
        System.out.println("오버로딩2");
        return a.toString();
    }

    void print(String a) {
        System.out.println("오버로딩3");
        System.out.println(a);
    }

    String print(Integer a, Integer b) {
        System.out.println("오버로딩4");
        return a.toString() + b.toString();
    }
}
```

- Overloding(오버로딩):  매개변수의 개수, 반환 타입 등의 다른 특징으로 같은 메서드를 정의하는 것 



![사진2](https://user-images.githubusercontent.com/81945553/129591820-6971a09d-df9e-423d-b719-96cebd591723.png)



### 상속

- 부모 클래스의 변수와 메서드를 자식 클래스가 물려 받아서 쓸 수 있는 것
- 상속과 다형성 차이
  - 상속 : 서브 클래스에서 슈퍼 클래스의 구조와 동작을 사용
  - 다형성 : 서브 클래스에서 슈퍼클래스의 동작을 변경
- 상속의 형태 : extends , implements, abstract 
  - extends : 부모에서 선언/정의를 모두 하여 자식은 메서드/변수를 그대로 사용
  - implements : 부모 객체는 선언만 하며, 내용은 자식에서 오버라이딩 해야 함
  - abstract : extends와 interface 혼합으로 extends 하되 몇 개는 추상 메서드로 구현 

> 부모가 두개 이상인 다중 상속은 지원하지 않지만, 인터페이스는 추상 클래스보다 더 추상적이므로 
>
> 여러 인터페이스를 상속받는 다중 상속을 지원한다. (인터페이스는 클래스가 아니다.)
>
> 인터페이스끼리 상속 받을 때는 extends, 인터페이스를 구현하는 클래스는 implements를 사용 

