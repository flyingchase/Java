# Java-Lambda

## 1 概念

lambda 表达式是一个代码块 必须传入相应的变量规范

- 无参数 仍然需要空括号 类似无参数方法

  ``` java
  () -> {(for(int i=0，i<=10；i++) i.sout;)}
  ```

- 可推导参数类型时 可忽略类型声明

- 可忽略 lambda 的返回类型 由上下文推导

  ``` java
  Comparator<String> comp = (first, last) ->(first.length()-last.length());
  // 无须声明 String first, String last
  ```

- 只有在某个分支有返回值 未考虑其他情况下返回值时 lambda 表达式不合法

  ``` java
  (int x) -> {(if (x>0) 
      return 1;
  )} 
  // 需要考虑 x<=0情况的返回值
  ```

  

### 1.01 常见 OOP 实现

- 定义函数——>创建对象使用函数

  ``` java
  public class LambdaConcept {
      public void printsth(String waitToPrint) {
          System.out.println(waitToPrint);
      }
      // 创建对象来调用函数
      public static void main(String[] args) {
          LambdaConcept lambdaDemo = new LambdaConcept();
          String waitToPrint = "This is the String waiting to print";
          lambdaDemo.printsth(waitToPrint);
      }
  }
  ```

- 创建功能接口——>对该接口定义抽象方法

  **函数式接口：** 只有一个抽象方法的接口 需要该接口的对象时 可以使用 lambda表达式作为参数传递

  ``` java
  Arrays.sort(words,
          (first, last)->(first.length() - last.length()));
  ```

  **常见的函数式接口:**

  - ArrayList 的 **removeIf** 方法的参数 Predicate 这个参数是接口 专门用于传递 lambda 表达式

    ``` java
    listTest.removeIf(e -> e == null);
    ```

  - Runable

  - Supplier\<T>

  - Consumer\<T>

  - Function<T,R>

  - Predicate \<T>

*函数式接口使用：*``@FunctionalInterface``对是否是函数式接口进行检查

定义函数式接口——>定义方法 方法参数为函数式接口——>使用 lambda 表达式为方法的参数

``` java
public class lambdaDemo {
    public static void main(String[] args) {
        // 使用 lambda表达式作为方法的参数
        Integer res = new lambdaDemo().operation(100, (x) -> x * x);
        System.out.println(res);
    }
    
    // 定义方法 参数为函数式接口
    public Integer operation(Integer num, FunctionalInterfaceDemo funcInterface) {
        return funcInterface.getValue(num);
    }

    // 定义函数式接口 只有一个抽象方法的接口
    /*接口没有() 无返回参数声明 */
    public interface FunctionalInterfaceDemo {
        public Integer getValue(Integer num);
    }
}
```





- **方法引用::**

  - method reference 指示编译器生成函数式接口的实例 覆盖该接口的抽象方法来调用给定的方法 lambda 表达式

    ```java
    Arrays.sort(strings,String::compareToIgnoreCase);
    ```

  - **::**运算符分割方法名称和对象/类名：

    - Object::instanceMethod 向方法传递参数的 lambda 表达式

      - `System.out::println`——>等价于`x-> System.out.println(x)`

    - Class::instanceMethod 第一个参数为作为方法的隐式参数

      - `String::compareToIgnoreCase`——>等价于`(x,y)->x.compareToIgnore(y)`

    - Class::staticMethod 所有参数都传递都静态方法

      - `Math::pow`——>等价于`Math.pow(x,y)`

      

      

      

    - 包含对象的方法引用::与 lambda 表达式有个细微区别：

      - `separator::equals` 当 separator为 null 时 构造的时候就会立即抛出 NullPointerException 而 x->separator.equals(x)只有在调用时候出现 NullPointerException

      

    - **构造器引用：**

      - 方法名称为 new  构造器调用的具体构造器由上下文推导

      



## 2. 处理 lambda 表达式

**延迟执行deferred execution：**

- 单独线程运行代码
- 多次运行代码
- 算法中恰当位置运行代码（排序中的 comp）
- 必要情况下执行代码（数据到达 按钮）



**Comparator 接口：**

- ```java
  Arrays.sort(people,Comparator.comparing(Person::getName));
  Arrays.sort(people,
          Comparator.comparing(Person::getLastsName)
          .thenComparing(Person::getFirstName));
  
  Arrays.sort(people, Comparator.comparing(Person::getName,
          (s,t)->Integer.compare(s.length(), t.length())));
  
  // 避免装箱
  Arrays.sort(people, Comparator.comparingInt(p->p.getName().length()));
  
  ```







## 3. lambda 注意事项

- lambda 表达式不是对象 可以看做为函数
- 方法引用:: 也不是对象 但为一个类型是函数式接口的变量赋值时会生成一个对象
- 