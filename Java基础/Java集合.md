<!-- TOC -->

- [集合](#集合)
  - [综述](#综述)
    - [方法](#方法)
    - [迭代器](#迭代器)
  - [`List`](#list)
    - [创建`List`](#创建list)
    - [遍历`List`](#遍历list)
    - [`List`和`Array`转换](#list和array转换)
    - [编写`equals`方法](#编写equals方法)
    - [`List`方法](#list方法)
    - [`ListIterator`方法](#listiterator方法)
    - [`LinkedList`方法](#linkedlist方法)
  - [`Queue`](#queue)
  - [`PriorityQueue`](#priorityqueue)
  - [`Deque`](#deque)
  - [`Map`](#map)
    - [`TreeMap`](#treemap)
  - [`Set`](#set)
  - [`Collections`](#collections)
  - [`Comparator`编写](#comparator编写)

<!-- /TOC -->
# 集合
## 综述
`Java`标准库自带的`java.util`包提供了集合类：`Collection`，它是除`Map`外所有其他集合类的根接口。`Java`的`java.util`包主要提供了`List`,`Set`,`Map`三种类型的集合。

`Java`集合的设计有几个特点：一是实现了接口和实现类相分离，例如，有序表的接口是`List`，具体的实现类有`ArrayList`，`LinkedList`等，二是支持泛型，我们可以限制在一个集合中只能放入同一种数据类型的元素。

最后，`Java`访问集合总是通过统一的方式——迭代器（`Iterator`）来实现，它最明显的好处在于无需知道集合内部元素是按什么方式存储的。

由于`Java`的集合设计非常久远，中间经历过大规模改进，我们要注意到有一小部分集合类是遗留类，不应该继续使用：
- `Hashtable`：一种线程安全的`Map`实现；
- `Vector`：一种线程安全的`List`实现；
- `Stack`：基于`Vector`实现的LIFO的栈。

还有一小部分接口是遗留接口，也不应该继续使用：
- `Enumeration<E>`：已被`Iterator<E>`取代。

![](https://picgp.oss-cn-beijing.aliyuncs.com/img/20200830140910.png)

![](https://picgp.oss-cn-beijing.aliyuncs.com/img/20200612124931.png)
### 方法
`Collection`接口声明了很多有用的方法，所有的实现类都必须提供这些方法：
- `int size()`:返回当前存储在集合中的元素个数
- `boolean isEmpty()`:如果集合中没有元素，返回`true`
- `Iterator iterator()`：返回一个`Iterator`对象，用于遍历集合里的元素。
- `boolean contains(Object obj)`:如果集合中包含了一个与`obj`相等的对象，返回`true`
- `boollean containsAll(Collection<?> other)`:如果这个集合包含了`other`集合中的所有元素，返回`true`
- `boolean add(E element)`：将一个元素添加到集合中，如果由于这个调用改变了集合，返回`true`
- `boolean addAll(Collection<? extends E> other)`:将`other`集合中的所有元素添加到这个集合，如果由于这个调用改变了集合，返回`true`
- `boolean remove(Object obj)`：从这个集合中删除等于`obj`的对象，如果有匹配的对象被删除，返回`true`,当集合中包括了一个或多个元素`obj`时，只删除第一个符合条件的元素。
- `boolean removeAll(Collection<?> other)`:从这个集合中删除`other`集合中存在的所有元素，如果由于这个调用改变了集合，返回`true`
- `default boolean removeIf(Predict<? super E> filter)`:从这个集合删除`filter`返回`true`的所有元素。如果由于这个调用改变了集合，则返回`true`。
- `void clear()`:从这个集合中删除所有元素。
- `boolean retainAll(Collection<?> other)`：从这个集合中删除所有与`other`集合中元素不同的元素。即取交集。如果由于这个调用改变了集合，返回`true`。
- `Object[] toArray()`：返回这个集合中的对象的数组。

当使用`System.out`的`println()`方法来输出集合对象时，将输出`[ele1,ele2...]`的形式，因为所有的`Collection`实现类都重写了`toString`方法。可以一次性输出集合中的所有元素。

`Java11`为`Collection`新增了一个`toArray(IntFunction)`方法，当`Collection`使用泛型时，`toArray(IntFunction)`可以返回特定类型的数组，而传统的`toArray()`方法，返回值总是`Object[]`。

```java
//该Collection使用了泛型，指定它的集合元素都是String
var strColl=List.of("Java","Kotlin","Swift","Python");
//toArray()方法参数是一个Lambda表达式，代表IntFunction对象
//此时toArray()方法的返回值类型是String[]，而不是Object[]
String[] sa=strColl.toArray(String[]::new);
```
末行示范了`toArray(IntFunction)`方法的特点，由于编译器推断`strColl`的类型为`List<String>`(带泛型)，因此`toArray(IntFunction)`方法参数通常就是它要返回的数组类型后面加双冒号和`new`(构造器引用)。
### 迭代器
`Java`迭代器位于两个元素之间，当调用`next`时，迭代器就越过下一个元素，并返回刚刚越过的那个元素的引用。

`Iterator`方法:
- `boolean hasNext()`:如果存在另一个可访问的元素，返回`true`
- `E next()`：返回将要访问的下一个对象，如果已经到了集合的末尾，将抛出一个`NoSuchElementException`。
- `void remove`:删除上次访问的对象，这个方法必须紧跟在访问一个元素后执行，如果上次访问之后集合发生了变化，这个方法将抛出一个`IllegalStateException`
- `default void forEachRemaining(Consumer<? super E> action)`:访问元素，并传递到指定动作，直到再没有更多元素，或者这个动作抛出一个异常。

删除第一个元素:
```java
Iterator<String> it=c.iterator();
it.next();//skip over the first element
it.remove();//now remove it
```
`next`方法和`remove`方法调用之间存在依赖性，如果调用`remove`之前没有调用`next`，将是不合法的，如果这样做，将会抛出一个`IllegalStatException`异常。
如果想删除两个相邻的元素，不能直接这样调用：
```java
it.remove();
it.remove();
```
实际上，必须要先调用`next`越过将要删除的元素：
```java
it.remove();
it.next();
it.remove();
```
当使用`Iterator`对集合元素进行迭代时，`Iterator`并不是把集合元素本身传递给了迭代变量，而是把集合元素的值传给了迭代变量，所以修改迭代变量的值对集合元素本身没有任何影响。

当使用`Iterator`迭代访问`Collection`集合元素时，`Collection`集合里的元素不能被改变，只有通过`Iterator`的`remove`方法删除上一次`next()`方法返回的集合元素才可以。即位于`Iterator`迭代块内修改`Collection`集合的方式会引发异常。

`Iterator`采用的是快速失败(`fail-fast`)机制，一旦在迭代中检测到该集合已经被修改，程序立即引发`ConcurrentModificationException`异常，而不是显示修改后的结果，这样可以避免共享资源引发的潜在问题。
## `List`
`List`作为`Collection`的子接口，可以使用`Collection`接口里的全部方法。

在`Java`程序设计语言中，所有链表实际都是双向链表。

定义一个整型数组列表，尖括号中的类型参数不允许是基本类型，也就是说，不允许写成`ArrayList<int>`。需要用到`Integer`包装类，可以声明一个`Integer`对象的数组列表:
```java
var list=new ArrayList<Integer>();
```

由于每个值分别包装在对象中，所以`ArrayList<Integer>`的效率远远低于`int[]`数组。因此，只有当程序员操作方便性比执行效率更重要的时候，才会考虑对较小的集合使用这种构造。

考察`List<E>`接口，可以看到几个主要的接口方法：
- 在末尾添加一个元素：`void add(E e)`
- 在指定索引添加一个元素：`void add(int index, E e)`
- 删除指定索引的元素：`int remove(int index)`
- 删除某个元素：`int remove(Object e)`
- 获取指定索引的元素：`E get(int index)`
- 获取链表大小（包含元素的个数）：`int size()`

![](https://picgp.oss-cn-beijing.aliyuncs.com/img/20200830142105.png)

`List`还允许添加`null`：
```java
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("apple"); // size=1
        list.add(null); // size=2
        list.add("pear"); // size=3
        String second = list.get(1); // null
        System.out.println(second);
    }
}
```
### 创建`List`
除了使用`ArrayList`和`LinkedList`，我们还可以通过`List`接口提供的`of()`方法，根据给定元素快速创建`List`：
```java
List<Integer> list = List.of(1, 2, 5);
```
但是`List.of()`方法不接受`null`值，如果传入`null`，会抛出`NullPointerException`异常。

### 遍历`List`
和数组类型，我们要遍历一个`List`，完全可以用`for`循环根据索引配合`get(int)`方法遍历：
```java
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("apple", "pear", "banana");
        for (int i=0; i<list.size(); i++) {
            String s = list.get(i);
            System.out.println(s);
        }
    }
}
```
但这种方式并不推荐:
1. 代码复杂
2. `get(int)`方法只有`ArrayList`的实现是高效的，换成`LinkedList`后，索引越大，访问速度越慢。

要始终坚持使用迭代器`Iterator`来访问`List`。通过`Iterator`遍历`List`永远是最高效的方式。`Iterator`本身也是一个对象，但它是由List的实例调用`iterator()`方法的时候创建的。`Iterator`对象知道如何遍历一个`List`，并且不同的`List`类型，返回的`Iterator`对象实现也是不同的，但总是具有最高的访问效率。

`Iterator`对象有两个方法：`boolean hasNext()`判断是否有下一个元素，`E next()`返回下一个元素。因此，使用`Iterator`遍历`List`代码如下：
```java
import java.util.Iterator;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("apple", "pear", "banana");
        for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
            String s = it.next();
            System.out.println(s);
        }
    }
}
```
`Java`的`for each`循环本身就可以帮我们使用`Iterator`遍历:
```java
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("apple", "pear", "banana");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
```
实际上，只要实现了`Iterable`接口的集合类都可以直接用`for each`循环来遍历，`Java`编译器本身并不知道如何遍历集合对象，但它会自动把`for each`循环变成`Iterator`的调用，原因就在于`Iterable`接口定义了一个`Iterator<E> iterator()`方法，强迫集合类必须返回一个`Iterator`实例。

### `List`和`Array`转换
给`toArray(T[])`传入一个类型相同的`Array`，`List`内部自动把元素复制到传入的`Array`中：
```java
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of(12, 34, 56);
        Integer[] array = list.toArray(new Integer[3]);
        for (Integer n : array) {
            System.out.println(n);
        }
    }
}
```
注意到这个`toArray(T[])`方法的泛型参数`<T>`并不是`List`接口定义的泛型参数`<E>`，所以，我们实际上可以传入其他类型的数组，例如我们传入`Number`类型的数组，返回的仍然是`Number`类型：
```java
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of(12, 34, 56);
        Number[] array = list.toArray(new Number[3]);
        for (Number n : array) {
            System.out.println(n);
        }
    }
}
```
但是，如果传入类型不匹配的数组，例如，`String[]`类型的数组，由于`List`的元素是`Integer`，所以无法放入`String`数组，这个方法会抛出`ArrayStoreException`。

如果传入的数组大小和`List`实际的元素个数不一致:

如果传入的数组不够大，那么`List`内部会创建一个新的刚好够大的数组，填充后返回；如果传入的数组比`List`元素还要多，那么填充完元素后，剩下的数组元素一律填充`null`。

实际上，最常用的是传入一个“恰好”大小的数组：
```java
Integer[] array = list.toArray(new Integer[list.size()]);
```

最后一种更简洁的写法是通过`List`接口定义的`T[] toArray(IntFunction<T[]> generator)`方法：
```java
Integer[] array = list.toArray(Integer[]::new);
```

把`Array`变为`List`通过`List.of(T...)`方法最简单：
```java
Integer[] array = { 1, 2, 3 };
List<Integer> list = List.of(array);
```

要注意的是，返回的`List`不一定就是`ArrayList`或者`LinkedList`，因为`List`只是一个接口，如果我们调用`List.of()`，它返回的是一个只读`List`：
```java
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of(12, 34, 56);
        list.add(999); // UnsupportedOperationException
    }
}
```
对只读`List`调用`add()`、`remove()`方法会抛出`UnsupportedOperationException`。

### 编写`equals`方法
`List`还提供了`boolean contains(Object o)`方法来判断`List`是否包含某个指定元素。此外，`int indexOf(Object o)`方法可以返回某个元素的索引，如果元素不存在，就返回-1。
```java
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("A", "B", "C");
        System.out.println(list.contains("C")); // true
        System.out.println(list.contains("X")); // false
        System.out.println(list.indexOf("C")); // 2
        System.out.println(list.indexOf("X")); // -1
    }
}
```
这里我们注意一个问题，我们往`List`中添加的`"C"`和调用`contains("C")`传入的`"C"`是不是同一个实例？

如果这两个`"C"`不是同一个实例，这段代码是否还能得到正确的结果？我们可以改写一下代码测试一下：
```java
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("A", "B", "C");
        System.out.println(list.contains(new String("C"))); // true
        System.out.println(list.indexOf(new String("C"))); // 2
    }
}
```
因为我们传入的是`new String("C")`，所以一定是不同的实例。结果仍然符合预期，这是为什么呢？

因为`List`内部并不是通过`==`判断两个元素是否相等，而是使用`equals()`方法判断两个元素是否相等，例如`contains()`方法可以实现如下：
```java
public class ArrayList {
    Object[] elementData;
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elementData[i])) {
                return true;
            }
        }
        return false;
    }
}
```
因此，要正确使用`List`的`contains()`、`indexOf()`这些方法，放入的实例必须正确覆写`equals()`方法，否则，放进去的实例，查找不到。我们之所以能正常放入`String`、`Integer`这些对象，是因为`Java`标准库定义的这些类已经正确实现了`equals()`方法。

```java
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Person> list = List.of(
            new Person("Xiao Ming"),
            new Person("Xiao Hong"),
            new Person("Bob")
        );
        System.out.println(list.contains(new Person("Bob"))); // false
    }
}

class Person {
    String name;
    public Person(String name) {
        this.name = name;
    }
}
```
不出意外，虽然放入了`new Person("Bob")`，但是用另一个`new Person("Bob")`查询不到，原因就是`Person`类没有覆写`equals()`方法。

`equals()`方法要求我们必须满足以下条件：
- 自反性（Reflexive）：对于非`null`的x来说，`x.equals(x)`必须返回`true`；
- 对称性（Symmetric）：对于非`null`的`x`和`y`来说，如果`x.equals(y)`为`true`，则`y.equals(x)`也必须为`true`；
- 传递性（Transitive）：对于非`null`的`x`、`y`和`z`来说，如果`x.equals(y)`为`true，y.equals(z)`也为`true`，那么`x.equals(z)`也必须为`true`；
- 一致性（Consistent）：对于非`null`的`x`和`y`来说，只要`x`和`y`状态不变，则`x.equals(y)`总是一致地返回`true`或者`false`；
- 对`null`的比较：即`x.equals(null)`永远返回`false`。

```java
public boolean equals(Object o) {
    if (o instanceof Person) {
        Person p = (Person) o;
        boolean nameEquals = false;
        if (this.name == null && p.name == null) {
            nameEquals = true;
        }
        if (this.name != null) {
            nameEquals = this.name.equals(p.name);
        }
        return nameEquals && this.age == p.age;
    }
    return false;
}
```
要简化引用类型的比较，我们使用`Objects.equals()`静态方法：
```java
public boolean equals(Object o) {
    if (o instanceof Person) {
        Person p = (Person) o;
        return Objects.equals(this.name, p.name) && this.age == p.age;
    }
    return false;
}
```

`equals()`方法的正确编写方法：
- 先确定实例“相等”的逻辑，即哪些字段相等，就认为实例相等；
- 用`instanceof`判断传入的待比较的`Object`是不是当前类型，如果是，继续比较，否则，返回`false`；
- 对引用类型用`Objects.equals()`比较，对基本类型直接用`==`比较。

使用`Objects.equals()`比较两个引用类型是否相等的目的是省去了判断`null`的麻烦。两个引用类型都是`null`时它们也是相等的。

如果不调用`List`的`contains()`、`indexOf()`这些方法，那么放入的元素就不需要实现`equals()`方法。

### `List`方法
- `ListIterator<E> listIterator()`:返回一个列表迭代器，用来访问列表中的元素。
- `ListIterator<E> listIterator(int index)`:返回一个列表迭代器，用来访问列表中的元素，第一次调用这个迭代器的`next`会返回给定索引的元素。
- `void add(E element)`:在末尾添加一个元素。
- `void add(int i,E element)`:在给定位置添加一个元素。
- `void addAll(int i,Collection<? extends E> elements)`:将一个集合中所有元素添加到给定位置。
- `E remove(int i)`:删除并返回给定位置的元素。
- `E get(int i)`:获取给定位置的元素。
- `E set(int i,E element)`:用一个新元素替换给定位置的元素，并返回原来的那个元素。
- `int indexOf(Object element)`:返回与指定元素相等的元素在列表中第一次出现的位置，如果没有这样的元素返回-1。
- `int lastIndexOf(Object element)`:返回与指定元素相等的元素在列表中最后一次出现的位置，如果没有这样的元素返回-1。
- `List subList(int fromIndex,int toIndex)`:返回从索引`fromIndex`到索引`toIndex`(不包含)处所有集合元素组成的子集合。
- `void sort(Comparator c)`:根据`Comparator`参数对`List`集合的元素排序。

`List`判断两个对象相等是通过`equals()`方法，`remove(Object obj)`,`indexOf(Object obj)`都可以传入`new Object()`来进行操作。

`sort()`方法需要一个`Comparator`对象来控制元素排序，程序可以使用`Lambda`表达式作为参数：
```java
var books=new ArrayList<String>();
books.add("abc");
books.add("asdaqwdq");
books.add("a");
books.sort((o1,o2)->o1.length()-o2.length());//[a, abc, asdaqwdq]
```
### `ListIterator`方法
- `void add(E element)`:在当前位置前添加一个元素。
- `void set(E newElement)`:用新元素替换`next`或`previous`访问的上一个元素，如果在上一个`next`或`previous`调用之后列表的结构被修改了，将抛出一个`IllegalStateException`异常。
- `boolean hasPrevious()`:当反向迭代列表时，如果还有可以访问的元素，返回`true`
- `E previous`():返回前一个对象，如果已经到达了列表的头部，就抛出一个`NoSuchElementException`异常。
- `int nextIndex()`:返回下一次调用`next`方法时将要返回的元素的索引。
- `int previousIndex()`:返回下一次调用`previous`方法时将返回的元素的索引。

使用`ListIterator`反向迭代时，开始也需要采用正向迭代：
```java
public static void main(String[] args){
    String[] books={"a","b","c"};
    var bookList=new ArrayList<String>();
    for(var i=0;i<books.length;++i){
        bookList.add(books[i]);
    }
    var lit=bookList.listIterator();
    while(lit.hasNext()){
        System.out.println(lit.next());
        lit.add("---分隔符---");
    }
    System.out.println("开始反向迭代");
    while(lit.hasPrevious()){
        System.out.println(lit.previous());
    }
}
```
### `LinkedList`方法
- `LinkedList()`：构造一个空列表。
- `LinkedList(Collection<? extends E> elements)`:构造一个链表，并将集合中所有的元素添加到这个链表中。
- `void addFirst(E element)`
- `void addLast(E element)`:将某个元素添加到头部或尾部。
- `E getFirst()`
- `E getLast()`:返回头部或尾部的元素。
- `E removeFirst()`
- `E removeLast()`:删除并返回头部或尾部的元素。
## `Queue`
在Java的标准库中，队列接口`Queue`定义了以下几个方法：
- `int size()`：获取队列长度；
- `boolean add(E element)`
- `boolean offer(E element)`:如果队列没有满，将给定的元素添加到这个队列的队尾并返回`true`，如果队列已满，第一个方法将抛出一个`IllegalStateException`，而第二个方法返回`false`。
- `E remove()`
- `E poll()`:假如队列不为空，删除并返回这个队列队头的元素，

注意：不要把`null`添加到队列中，否则`poll()`方法返回`null`时，很难确定是取到了`null`元素还是队列为空。

`Queue`接口有一个`PriorityQueue`实现类，除此之外，还有一个`Deque`接口，`Java`为`Deque`提供了`ArrayDeque`和`LinkedList`两个实现类。
## `PriorityQueue`
初始化:

```java
Queue<type> q = new PriorityQueue<>();
Queue<type> q =new PriorityQueue<>(int initialCapacity);//确定大小
Queue<type> q =new PriorityQueue<>(int initialCapacity,Comparator<? super E> c);//确定大小，使用指定的比较器。
```
优先队列既可以保存实现了`Comparable`接口的类对象，也可以保存构造器中提供的`Comparator`对象。
大根堆:
```java
PriorityQueue<Integer> queue=new PriorityQueue<>((a,b)->b-a);
```

```java
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<User> q = new PriorityQueue<>(new UserComparator());
        // 添加3个元素到队列:
        q.offer(new User("Bob", "A1"));
        q.offer(new User("Alice", "A2"));
        q.offer(new User("Boss", "V1"));
        System.out.println(q.poll()); // Boss/V1
        System.out.println(q.poll()); // Bob/A1
        System.out.println(q.poll()); // Alice/A2
        System.out.println(q.poll()); // null,因为队列为空
    }
}

class UserComparator implements Comparator<User> {
    public int compare(User u1, User u2) {
        if (u1.number.charAt(0) == u2.number.charAt(0)) {
            // 如果两人的号都是A开头或者都是V开头,比较号的大小:
            return u1.number.compareTo(u2.number);
        }
        if (u1.number.charAt(0) == 'V') {
            // u1的号码是V开头,优先级高:
            return -1;
        } else {
            return 1;
        }
    }
}

class User {
    public final String name;
    public final String number;

    public User(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String toString() {
        return name + "/" + number;
    }
}
```
## `Deque`
初始化
```java
Deque<type> d=new LinkedList<>();
Deque<type> d=new ArrayDeque<>();
```
`ArrayDeque`是一个基于数组实现的双端队列，创建一个`Deque`时同样可以指定一个`numElements`参数，该参数用于指定`Object[]`数组的长度，如果不指定`numElements`参数，`Deque`底层数组的长度为16。

`ArrayList`和`ArrayDeque`两个集合类的实现机制基本类似，他们的底层都采用一个动态的，可重分配的`Object[]`数组来存储集合元素，当集合元素超出该数组的容量时，系统会在底层重新分配一个`Object[]`数组来存储集合元素。
- `void addFirst(E element)`
- `void addLast(E element)`
- `void offerFirst(E element)`
- `void offerlast(E element)`
将给定的对象添加到双端队列的队头或队尾，如果这个双端队列已满，前面两个方法将抛出一个`IllegalStateException`，而后两个方法返回`false`
- `E removeFirst()`
- `E removeLast()`
- `E pollFirst()`
- `E pollLast()`
  如果这个双端队列不为空，删除并返回双端队列队头或队尾的元素，如果双端队列为空，前面两个方法将抛出一个`NoSuchElementException`，而后面两个方法返回`null`
- `E getFirst()`
- `E getLast()`
- `E peekFirst()`
- `E peekLast()`
如果这两个双端队列为空，返回双端队列队头或队尾的元素，但不删除。如果双端队列为空，前面两个方法将抛出一个`NoSuchElementException`,而后面两个方法返回`null`
- `E pop()(栈方法)`:相当于`removeFirst()`
- `E push()(栈方法)`:相当于`addFirst()`
## `Map`
`Map`提供了一个`Entry`内部类来封装`key-value`对，而计算`Entry`存储时则只考虑`Entry`封装的`key`，从`Java`源码来看，`Java`是先实现了`Map`，然后通过包装一个所有`value`都为空对象的`Map`就实现了`Set`集合。

`HashMap`之所以能根据`key`直接拿到`value`，原因是它内部通过空间换时间的方法，用一个大数组存储所有`value`，并根据`key`直接计算出`value`应该存储在哪个索引：

![](https://picgp.oss-cn-beijing.aliyuncs.com/img/20200830194659.png)

如果`key`的值为`"a"`，计算得到的索引总是1，因此返回`value`为`Person("Xiao Ming")`，如果`key`的值为`"b"`，计算得到的索引总是5，因此返回`value`为`Person("Xiao Hong")`，这样，就不必遍历整个数组，即可直接读取`key`对应的`value`。

当我们使用`key`存取`value`的时候，就会引出一个问题：

我们放入`Map`的`key`是字符串`"a"`，但是，当我们获取`Map`的`value`时，传入的变量不一定就是放入的那个`key`对象。

换句话讲，两个`key`应该是内容相同，但不一定是同一个对象。测试代码如下：
```java
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String key1 = "a";
        Map<String, Integer> map = new HashMap<>();
        map.put(key1, 123);

        String key2 = new String("a");
        map.get(key2); // 123

        System.out.println(key1 == key2); // false
        System.out.println(key1.equals(key2)); // true
    }
}
```
因为在`Map`的内部，对`key`做比较是通过`equals()`实现的，这一点和`List`查找元素需要正确覆写`equals()`是一样的，即正确使用`Map`必须保证：作为`key`的对象必须正确覆写`equals()`方法。

我们经常使用`String`作为`key`，因为`String`已经正确覆写了`equals()`方法。但如果我们放入的`key`是一个自己写的类，就必须保证正确覆写了`equals()`方法。

我们再思考一下`HashMap`为什么能通过`key`直接计算出`value`存储的索引。相同的`key`对象（使用`equals()`判断时返回`true`）必须要计算出相同的索引，否则，相同的`key`每次取出的`value`就不一定对。

通过`key`计算索引的方式就是调用`key`对象的`hashCode()`方法，它返回一个`int`整数。`HashMap`正是通过这个方法直接定位`key`对应的`value`的索引，继而直接返回`value`。

因此，正确使用`Map`必须保证：

1. 作为`key`的对象必须正确覆写`equals()`方法，相等的两个`key`实例调用`equals()`必须返回`true`；
2. 作为`key`的对象还必须正确覆写`hashCode()`方法，且`hashCode()`方法要严格遵循以下规范：
   - 如果两个对象相等，则两个对象的`hashCode()`必须相等； 
   - 如果两个对象不相等，则两个对象的`hashCode()`尽量不要相等。

即对应两个实例`a`和`b`：

- 如果`a`和`b`相等，那么`a.equals(b)`一定为`true`，则`a.hashCode()`必须等于`b.hashCode()`；
- 如果`a`和`b`不相等，那么`a.equals(b)`一定为`false`，则`a.hashCode()`和`b.hashCode()`尽量不要相等。


上述第一条规范是正确性，必须保证实现，否则`HashMap`不能正常工作。

而第二条如果尽量满足，则可以保证查询效率，因为不同的对象，如果返回相同的`hashCode()`，会造成`Map`内部存储冲突，使存取的效率下降。

正确编写`equals()`的方法以`Person`类为例：
```java
public class Person {
    String firstName;
    String lastName;
    int age;
}
```
把需要比较的字段找出来：
- `firstName`
- `lastName`
- `age`

然后，引用类型使用`Objects.equals()`比较，基本类型使用`==`比较。

在正确实现`equals()`的基础上，我们还需要正确实现`hashCode()`，即上述3个字段分别相同的实例，`hashCode()`返回的`int`必须相同：

```java
public class Person {
    String firstName;
    String lastName;
    int age;

    @Override
    int hashCode() {
        int h = 0;
        h = 31 * h + firstName.hashCode();
        h = 31 * h + lastName.hashCode();
        h = 31 * h + age;
        return h;
    }
}
```

注意到`String`类已经正确实现了`hashCode()`方法，我们在计算`Person`的`hashCode()`时，反复使用`31*h`，这样做的目的是为了尽量把不同的`Person`实例的`hashCode()`均匀分布到整个`int`范围。

和实现`equals()`方法遇到的问题类似，如果`firstName`或`lastName为null`，上述代码工作起来就会抛`NullPointerException`。为了解决这个问题，我们在计算`hashCode()`的时候，经常借助`Objects.hash()`来计算：
```java
int hashCode() {
    return Objects.hash(firstName, lastName, age);
}
```

所以，编写`equals()`和`hashCode()`遵循的原则是：

`equals()`用到的用于比较的每一个字段，都必须在`hashCode()`中用于计算；`equals()`中没有使用到的字段，绝不可放在`hashCode()`中计算。

另外注意，对于放入`HashMap`的`value`对象，没有任何要求。

既然`HashMap`内部使用了数组，通过计算`key`的`hashCode()`直接定位`value`所在的索引，那么第一个问题来了：`hashCode()`返回的`int`范围高达±21亿，先不考虑负数，`HashMap`内部使用的数组得有多大？

实际上`HashMap`初始化时默认的数组大小只有16，任何`key`，无论它的`hashCode()`有多大，都可以简单地通过：

```java
int index = key.hashCode() & 0xf; // 0xf = 15
```

把索引确定在0～15，即永远不会超出数组范围，上述算法只是一种最简单的实现。

第二个问题：如果添加超过16个`key-value`到`HashMap`，数组不够用了怎么办？

添加超过一定数量的`key-value`时，`HashMap`会在内部自动扩容，每次扩容一倍，即长度为16的数组扩展为长度32，相应地，需要重新确定`hashCode()`计算的索引位置。例如，对长度为32的数组计算`hashCode()`对应的索引，计算方式要改为：

```java
int index = key.hashCode() & 0x1f; // 0x1f = 31
```

由于扩容会导致重新分布已有的`key-value`，所以，频繁扩容对`HashMap`的性能影响很大。如果我们确定要使用一个容量为10000个`key-value`的`HashMap`，更好的方式是创建`HashMap`时就指定容量：

```java
Map<String, Integer> map = new HashMap<>(10000);
```

虽然指定容量是10000，但`HashMap`内部的数组长度总是$2^n$，因此，实际数组长度被初始化为比10000大的16384（$2^14$）。

最后一个问题：如果不同的两个`key`，例如`"a"`和`"b"`，它们的`hashCode()`恰好是相同的（这种情况是完全可能的，因为不相等的两个实例，只要求`hashCode()`尽量不相等），那么，当我们放入：

```java
map.put("a", new Person("Xiao Ming"));
map.put("b", new Person("Xiao Hong"));
```

时，由于计算出的数组索引相同，后面放入的`"Xiao Hong"`会不会把`"Xiao Ming"`覆盖了？

当然不会！使用`Map`的时候，只要`key`不相同，它们映射的`value`就互不干扰。但是，在`HashMap`内部，确实可能存在不同的`key`，映射到相同的`hashCode()`，即相同的数组索引上,

我们就假设`"a"`和`"b"`这两个`key`最终计算出的索引都是5，那么，在`HashMap`的数组中，实际存储的不是一个`Person`实例，而是一个`List`，它包含两个`Entry`，一个是`"a"`的映射，一个是`"b"`的映射：

![](https://picgp.oss-cn-beijing.aliyuncs.com/img/20200830202219.png)

在查找的时候，例如：
```java
Person p = map.get("a");
```
`HashMap`内部通过`"a"`找到的实际上是`List<Entry<String, Person>>`，它还需要遍历这个`List`，并找到一个`Entry`，它的`key`字段是`"a"`，才能返回对应的`Person`实例。

我们把不同的`key`具有相同的`hashCode()`的情况称之为哈希冲突。在冲突的时候，一种最简单的解决办法是用`List`存储`hashCode()`相同的`key-value`。显然，如果冲突的概率越大，这个`List`就越长，`Map`的`get()`方法效率就越低，这就是为什么要尽量满足条件二：

- 如果两个对象不相等，则两个对象的`hashCode()`尽量不要相等。

`hashCode()`方法编写得越好，`HashMap`工作的效率就越高。

对`Map`来说，要遍历`key`可以使用`for each`循环遍历`Map`实例的`keySet()`方法返回的`Set`集合，它包含不重复的`key`的集合：

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 123);
        map.put("pear", 456);
        map.put("banana", 789);
        for (String key : map.keySet()) {
            Integer value = map.get(key);
            System.out.println(key + " = " + value);
        }
    }
}
```

同时遍历`key`和`value`可以使用`for each`循环遍历`Map`对象的`entrySet()`集合，它包含每一个`key-value`映射：

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 123);
        map.put("pear", 456);
        map.put("banana", 789);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " = " + value);
        }
    }
}
```

方法:
- `void clear()`:删除该`Map`对象中所有的`key-value`对
- `Object get(Object key)`：返回指定`key`所对应的`value`，如果此`Map`中不包含该`key`，则返回`null`
- `Object getOrDefault(Object key,V defaultValue)`:获取指定`key`对应的`value`，如果该`key`不存在，则返回`defaultKey`
- `boolean containsKey(Object key)`:查询`Map`中是否包含指定的`key`，如果包含则返回`true`
- `boolean containsValue(Object value)`：查询`Map`中是否包含一个或多个`value`，如果包含则返回`true`
- `Set entrySet()`:返回`Map`中包含的`key-value`对所组成的`Set`组合，每个集合元素都是`Map.Entry`(`Entry`是`Map`的内部类)对象
- `boolean isEmpty()`：查询该`Map`是否为空如果为空则返回`true`
- `Set keySet()`:返回该`Map`中所有`key`组成的`Set`集合
- `Object put(Object key,Object value)`：添加一个`key-value`对，如果当前`Map`中已有一个与该`key`相等的`key-value`对，则新的`key-value`对会覆盖原来的`key-value`对
- `Object remove(Object key)`:删除指定`key`所对应的`key-value`对，返回被删除`key`所关联的`value`，如果该`key`不存在，则返回`null`
- `boolean remove(Object key,Object value)`:`Java8`新增的方法，删除指定的`key`,`value`所对应的`key-value`对，如果从该`Map`中成功删除该`key-value`对，该方法返回`true`，否则返回`false`
- `int size()`:返回`key-value`对的个数

`Map`中包含一个内部类`Entry`，该类封装了一个`key-value`对，包含如下三个方法
- `Object getKey()`:返回该`Entry`里包含的`key`值
- `Object getValue()`:返回该`Entry`里包含的`value`值
- `Object setValue(V value)`：设置该`Entry`里包含的`value`值，并返回新设置的`value`值

### `TreeMap`
`TreeMap`是一个红黑树数据结构，每个`key-value`对即作为红黑树的一个节点，`TreeMap`存储`key-value`对(节点)时，需要根据`key`对节点进行排序，`TreeMap`可以保证所有的`key-value`对处于有序状态，`TreeMap`也有两种排序方式：
1. 自然排序:`TreeMap`的所有`key`必须实现`Comparable`接口，而且所有的`key`应该是同一个类的对象，否则会抛出`ClassCastException`异常。
2. 定制排序:创建`TreeMap`时，传入一个`Comparator`对象，该对象负责对`TreeMap`中的所有`key`进行排序，采用定制排序是不要求`Map`的`key`实现`Comparator`接口。

`TreeMap`中判断两个`key`相等的标准是:两个`Key`通过`CompareTo()`的方法返回0，`TreeMap`即认为这两个`key`是相等的。

如果使用自定义类作为`TreeMap`的`key`，且想让`TreeMap`良好地工作，则重写该类的`equals()`方法和`comparaTo()`方法时应保持一致的返回结果，两个`key`通过`equals`方法比较返回`true`时，它们通过`compareTo()`方法比较应该返回0，如果`equals()`方法与`comparaTo()`方法返回的结果不一致，`TreeMap`和`Map`接口的规则就会冲突。

`TreeMap`也提供了一系列根据`key`顺序访问`key-value`对的方法

- `Map.Entry firstEntry()`:返回该`Map`中最小`key`所对应的`key-value`对，如果该`Map`为空，则返回`null`
- `Object firstKey()`:返回该`Map`中的最小`key`值，如果该`Map`为空，则返回`null`
- `Map.Entry lastEntry()`:返回该`Map`中的最大`key`值，如果该`Map`为空或者不存在这样的`key`，则都返回`null`
- `Object lastKey()`:返回该`Map`中的最大`key`值，如果该`Map`为空或不存在这样的`key`，则都返回`null`

`SortedMap`是接口，它的实现类是`TreeMap`。

使用`TreeMap`时，放入的`Key`必须实现`Comparable`接口。`String`、`Integer`这些类已经实现了`Comparable`接口，因此可以直接作为`Key`使用。作为`Value`的对象则没有任何要求。

如果作为`Key`的`class`没有实现`Comparable`接口，那么，必须在创建`TreeMap`时同时指定一个自定义排序算法：
```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<Person, Integer> map = new TreeMap<>(new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                return p1.name.compareTo(p2.name);
            }
        });
        map.put(new Person("Tom"), 1);
        map.put(new Person("Bob"), 2);
        map.put(new Person("Lily"), 3);
        for (Person key : map.keySet()) {
            System.out.println(key);
        }
        // {Person: Bob}, {Person: Lily}, {Person: Tom}
        System.out.println(map.get(new Person("Bob"))); // 2
    }
}

class Person {
    public String name;
    Person(String name) {
        this.name = name;
    }
    public String toString() {
        return "{Person: " + name + "}";
    }
}
```
注意到`Comparator`接口要求实现一个比较方法，它负责比较传入的两个元素`a`和`b`，如果`a<b`，则返回负数，通常是-1，如果`a==b`，则返回0，如果`a>b`，则返回正数，通常是1。`TreeMap`内部根据比较结果对`Key`进行排序。

从上述代码执行结果可知，打印的`Key`确实是按照`Comparator`定义的顺序排序的。如果要根据`Key`查找`Value`，我们可以传入一个`new Person("Bob")`作为Key，它会返回对应的`Integer`值2

另外，注意到`Person`类并未覆写`equals()`和`hashCode()`，因为`TreeMap`不使用`equals()`和`hashCode()`。

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<Student, Integer> map = new TreeMap<>(new Comparator<Student>() {
            public int compare(Student p1, Student p2) {
                return p1.score > p2.score ? -1 : 1;
            }
        });
        map.put(new Student("Tom", 77), 1);
        map.put(new Student("Bob", 66), 2);
        map.put(new Student("Lily", 99), 3);
        for (Student key : map.keySet()) {
            System.out.println(key);
        }
        System.out.println(map.get(new Student("Bob", 66))); // null?
    }
}

class Student {
    public String name;
    public int score;
    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
    public String toString() {
        return String.format("{%s: score=%d}", name, score);
    }
}
```
根据相同的`Key`：`new Student("Bob", 66)`进行查找时，结果为`null`

在这个例子中，`TreeMap`出现问题，原因其实出在这个`Comparator`上：

```java
public int compare(Student p1, Student p2) {
    return p1.score > p2.score ? -1 : 1;
}
```

在`p1.score`和`p2.score`不相等的时候，它的返回值是正确的，但是，在`p1.score`和`p2.score`相等的时候，它并没有返回0！这就是为什么`TreeMap`工作不正常的原因：`TreeMap`在比较两个`Key`是否相等时，依赖`Key`的`compareTo()`方法或者`Comparator.compare()`方法。在两个`Key`相等时，必须返回0。因此，修改代码如下：
```java
public int compare(Student p1, Student p2) {
    if (p1.score == p2.score) {
        return 0;
    }
    return p1.score > p2.score ? -1 : 1;
}
```
或者直接借助`Integer.compare(int, int)`也可以返回正确的比较结果。



## `Set`
当向`HashSet`中存入一个元素时，`HashSet`会调用该对象的`hashCode`方法来得到该对象的`hashCode`值，然后根据该`hashCode`值决定该对象在`HashSet`中的存储位置，如果有两个元素通过`eauqls()`方法比较返回`true`，但它们的`hashCode()`方法返回值不相等,`HashSet`将会把它们存储在不同的位置，依然可以添加成功。

也就是说，`HashSet`集合判断两个元素相等的标准是两个对象通过`equals()`方法比较相等，并且两个对象的`hashCode()`方法返回值也相等。

方法:

- 将元素添加进`Set<E>`：`boolean add(E e)`
- 将元素从`Set<E>`删除：`boolean remove(Object e)`
- 判断是否包含元素：`boolean contains(Object e)`

`TreeSet`可以确保集合元素处于排序状态。

方法

- `Object first()`:返回集合中第一个元素。
- `Object last()`:返回集合中最后一个元素。





自定义类加入 set 必须实现 hashCode 和 equal

## `Collections`
`Java`提供了一个操作`Set`,`List`,`Map`等集合的工具类，该工具类里提供了大量方法对集合元素进行排序，查询和修改操作，还提供了将集合对象设置为不可变，对集合对象实现同步控制等方法。

方法

- `void reverse(List list)`:反转指定`List`集合中元素的顺序。
- `void sort(List list)`:根据元素的自然顺序对指定`List`集合的元素按升序进行排序
- `void sort(List list,Comparator c)`:根据指定`Comparator`产生的顺序对`List`集合元素进行排序。
- `void swap(List list,int i,int j)`:将指定`List`集合中的`i`处元素与`j`处元素进行交换。
- `int binarySearch(List list,Object key)`:使用二分搜索法搜索指定的`List`集合，以获得指定对象在`List`集合中的索引，`List`中的元素必须处于有序状态。
- `Object max(Collection coll)`:根据元素的自然顺序，返回给定集合中的最大元素。
- `Object max(Collection coll,Comparator comp)`:根据`Comparator`指定的顺序，返回给定集合中的最大元素。
- `Object min(Collection coll)`:根据元素的自然顺序，返回给定集合中的最小元素。
- `Object min(Collection coll,Comparator comp)`:根据`Comparator`指定的顺序，返回给定集合中的最小元素。
- `void fill(List list,Object obj)`:使用指定元素`obj`替换`List`集合中的所有元素。
- `int frequency(Collection c,Object obj)`:返回指定集合中指定元素的出现次数

## `Comparator`编写
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class A {
    int a;

    public A(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "[a=" + a + "]";
    }

}

class MyComparator implements Comparator<A> {

    @Override
    public int compare(A o1, A o2) {
        //升序
        //return o1.a - o2.a;
        //降序：后面会具体分析为什么降序
        return o2.a - o1.a;
    }

}

public class ComparatorTest {

    public static void main(String[] args) {
        A a1 = new A(5);
        A a2 = new A(7);
        List<A> list = new ArrayList<A>();
        list.add(a1);
        list.add(a2);
        Collections.sort(list, new MyComparator());
        System.out.println(list);
    }

}
```
首先`o2`是第二个元素，`o1`是第一个元素。无非就以下这些情况：
- `o2.a > o1.a` : 那么此时返回正数，表示需要调整`o1`,`o2`的顺序，也就是需要把`o2`放到`o1`前面，这不就是降序了么。
- `o2.a < o1.a` : 那么此时返回负数，表示不需要调整，也就是此时`o1` 比 `o2`大， 不还是降序么。 

经典 `Comparator` 示例：
```java
Comparator<Developer> byName = new Comparator<Developer>() {
            @Override
            public int compare(Developer developer, Developer compareDeveloper) {
                return developer.getName().compareTo(compareDeveloper.getName());
            }
        };
```

对应的 `Lambda` 表达式示例：
```java
 Comparator<Developer> byNameLambda =(Developer developer, Developer compareDeveloper)->developer.getName().compareTo(compareDeveloper.getName());
```

 比较 `Developer`的对象的 `age` 的示例。通常使用 `Collections.sort` 并传递一个这样的匿名`Comparator`类：
```java
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestSorting {

    public static void main(String[] args) {

        List<Developer> listDevs = getDevelopers();

        System.out.println("Before Sort");
        for (Developer developer : listDevs) {
            System.out.println(developer);
        }

        //sort by age
        Collections.sort(listDevs, new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        System.out.println("After Sort");
        for (Developer developer : listDevs) {
            System.out.println(developer);
        }

    }

    private static List<Developer> getDevelopers() {

        List<Developer> result = new ArrayList<Developer>();

        result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
        result.add(new Developer("alvin", new BigDecimal("80000"), 20));
        result.add(new Developer("jason", new BigDecimal("100000"), 10));
        result.add(new Developer("iris", new BigDecimal("170000"), 55));

        return result;

    }

}
```
在`Java 8`中，`List` 接口支持直接使用 `sort` 该方法，不再需要使用 `Collections.sort` 了。

```java
//List.sort() since Java 8
listDevs.sort(new Comparator<Developer>() {
    @Override
    public int compare(Developer o1, Developer o2) {
        return o2.getAge() - o1.getAge();
    }
});
```
```java
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestSorting {

    public static void main(String[] args) {

        List<Developer> listDevs = getDevelopers();

        System.out.println("Before Sort");
        for (Developer developer : listDevs) {
            System.out.println(developer);
        }

        System.out.println("After Sort");

        //lambda here!
        listDevs.sort((Developer o1, Developer o2)->o1.getAge()-o2.getAge());

        //java 8 only, lambda also, to print the List
        listDevs.forEach((developer)->System.out.println(developer));
    }

    private static List<Developer> getDevelopers() {

        List<Developer> result = new ArrayList<Developer>();

        result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
        result.add(new Developer("alvin", new BigDecimal("80000"), 20));
        result.add(new Developer("jason", new BigDecimal("100000"), 10));
        result.add(new Developer("iris", new BigDecimal("170000"), 55));
-
        return result;

    }

}
```

按年龄排序:

```java
//sort by age
Collections.sort(listDevs, new Comparator<Developer>() {
    @Override
    public int compare(Developer o1, Developer o2) {
        return o1.getAge() - o2.getAge();
    }
});

//lambda
listDevs.sort((Developer o1, Developer o2)->o1.getAge()-o2.getAge());

//lambda, valid, parameter type is optional
listDevs.sort((o1, o2)->o1.getAge()-o2.getAge());
// lambda
listDevs.sort(Comparator.comparing(Developer::getAge));
```

按名称排序:
```java
//sort by name
Collections.sort(listDevs, new Comparator<Developer>() {
    @Override
    public int compare(Developer o1, Developer o2) {
        return o1.getName().compareTo(o2.getName());
    }
});

//lambda
listDevs.sort((Developer o1, Developer o2)->o1.getName().compareTo(o2.getName()));

//lambda
listDevs.sort((o1, o2)->o1.getName().compareTo(o2.getName()));
// lambda
listDevs.sort(Comparator.comparing(Developer::getName));
```

按薪水排序

```java
//sort by salary
Collections.sort(listDevs, new Comparator<Developer>() {
    @Override
    public int compare(Developer o1, Developer o2) {
        return o1.getSalary().compareTo(o2.getSalary());
    }
});

//lambda
listDevs.sort((Developer o1, Developer o2)->o1.getSalary().compareTo(o2.getSalary()));

//lambda
listDevs.sort((o1, o2)->o1.getSalary().compareTo(o2.getSalary()));
// lambda
listDevs.sort(Comparator.comparing(Developer::getSalary));
```

使用`Lambda`表达式对列表进行工资由少到多的排序。

```java
Comparator<Developer> salaryComparator = (o1, o2)->o1.getSalary().compareTo(o2.getSalary());
listDevs.sort(salaryComparator);
```

使用`Lambda`表达式对列表进行工资由多到少的排序。

```java
Comparator<Developer> salaryComparator = (o1, o2)->o1.getSalary().compareTo(o2.getSalary());
listDevs.sort(salaryComparator.reversed());
```



















# 面试题收集

## 1）说说常见的集合有哪些吧？

答：Map接口和Collection接口是所有集合框架的父接口：

1. Collection接口的子接口包括：Set接口和List接口
   1. Map接口的实现类主要有：HashMap、TreeMap、Hashtable、ConcurrentHashMap以及Properties等
   2. Set接口的实现类主要有：HashSet、TreeSet、LinkedHashSet等
2. List接口的实现类主要有：ArrayList、LinkedList、Stack以及Vector等

------

## 2）HashMap与HashTable的区别？

答：

1. HashMap没有考虑同步，是线程不安全的；Hashtable使用了synchronized关键字，是线程安全的；
2. HashMap允许K/V都为null；后者K/V都不允许为null；
3. HashMap继承自AbstractMap类；而Hashtable继承自Dictionary类；

------

## 3）HashMap的put方法的具体流程？

答：下面先来分析一下源码

```java
COPYfinal V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    HashMap.Node<K,V>[] tab; HashMap.Node<K,V> p; int n, i;
    // 1.如果table为空或者长度为0，即没有元素，那么使用resize()方法扩容
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    // 2.计算插入存储的数组索引i，此处计算方法同 1.7 中的indexFor()方法
    // 如果数组为空，即不存在Hash冲突，则直接插入数组
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    // 3.插入时，如果发生Hash冲突，则依次往下判断
    else {
        HashMap.Node<K,V> e; K k;
        // a.判断table[i]的元素的key是否与需要插入的key一样，若相同则直接用新的value覆盖掉旧的value
        // 判断原则equals() - 所以需要当key的对象重写该方法
        if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        // b.继续判断：需要插入的数据结构是红黑树还是链表
        // 如果是红黑树，则直接在树中插入 or 更新键值对
        else if (p instanceof HashMap.TreeNode)
            e = ((HashMap.TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        // 如果是链表，则在链表中插入 or 更新键值对
        else {
            // i .遍历table[i]，判断key是否已存在：采用equals对比当前遍历结点的key与需要插入数据的key
            //    如果存在相同的，则直接覆盖
            // ii.遍历完毕后任务发现上述情况，则直接在链表尾部插入数据
            //    插入完成后判断链表长度是否 > 8：若是，则把链表转换成红黑树
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        // 对于i 情况的后续操作：发现key已存在，直接用新value覆盖旧value&返回旧value
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    // 插入成功后，判断实际存在的键值对数量size > 最大容量
    // 如果大于则进行扩容
    if (++size > threshold)
        resize();
    // 插入成功时会调用的方法（默认实现为空）
    afterNodeInsertion(evict);
    return null;
}
```

图片简单总结为：



[![img](https://cdn.jsdelivr.net/gh/wmyskxz/img/img/Java%E9%9B%86%E5%90%88%E5%BF%85%E4%BC%9A14%E9%97%AE%EF%BC%88%E7%B2%BE%E9%80%89%E9%9D%A2%E8%AF%95%E9%A2%98%E6%95%B4%E7%90%86%EF%BC%89/7896890-cf5aa3b08dd9684f.png)](https://cdn.jsdelivr.net/gh/wmyskxz/img/img/Java集合必会14问（精选面试题整理）/7896890-cf5aa3b08dd9684f.png)



------

## 4）HashMap的扩容操作是怎么实现的？

答：通过分析源码我们知道了HashMap通过`resize()`方法进行扩容或者初始化的操作，下面是对源码进行的一些简单分析：

```java
COPY/**
 * 该函数有2中使用情况：1.初始化哈希表；2.当前数组容量过小，需要扩容
 */
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table;// 扩容前的数组（当前数组）
    int oldCap = (oldTab == null) ? 0 : oldTab.length;// 扩容前的数组容量（数组长度）
    int oldThr = threshold;// 扩容前数组的阈值
    int newCap, newThr = 0;

    if (oldCap > 0) {
        // 针对情况2：若扩容前的数组容量超过最大值，则不再扩容
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        }
        // 针对情况2：若没有超过最大值，就扩容为原来的2倍（左移1位）
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                oldCap >= DEFAULT_INITIAL_CAPACITY)
            newThr = oldThr << 1; // double threshold
    }

    // 针对情况1：初始化哈希表（采用指定或者使用默认值的方式）
    else if (oldThr > 0) // initial capacity was placed in threshold
        newCap = oldThr;
    else {               // zero initial threshold signifies using defaults
        newCap = DEFAULT_INITIAL_CAPACITY;
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }

    // 计算新的resize上限
    if (newThr == 0) {
        float ft = (float)newCap * loadFactor;
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                (int)ft : Integer.MAX_VALUE);
    }
    threshold = newThr;
    @SuppressWarnings({"rawtypes","unchecked"})
    Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    table = newTab;
    if (oldTab != null) {
        // 把每一个bucket都移动到新的bucket中去
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;
                else if (e instanceof TreeNode)
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                else { // preserve order
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    do {
                        next = e.next;
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}
```

------

## 5）HashMap是怎么解决哈希冲突的？



答：在解决这个问题之前，我们首先需要知道**什么是哈希冲突**，而在了解哈希冲突之前我们还要知道**什么是哈希**才行；

### 什么是哈希？

**Hash，一般翻译为“散列”，也有直接音译为“哈希”的，这就是把任意长度的输入通过散列算法，变换成固定长度的输出，该输出就是散列值（哈希值）；**这种转换是一种压缩映射，也就是，散列值的空间通常远小于输入的空间，不同的输入可能会散列成相同的输出，所以不可能从散列值来唯一的确定输入值。**简单的说就是一种将任意长度的消息压缩到某一固定长度的消息摘要的函数。**

所有散列函数都有如下一个基本特性：**根据同一散列函数计算出的散列值如果不同，那么输入值肯定也不同。但是，根据同一散列函数计算出的散列值如果相同，输入值不一定相同。**

### 什么是哈希冲突？

**当两个不同的输入值，根据同一散列函数计算出相同的散列值的现象，我们就把它叫做碰撞（哈希碰撞）。**

### HashMap的数据结构

在Java中，保存数据有两种比较简单的数据结构：数组和链表。**数组的特点是：寻址容易，插入和删除困难；链表的特点是：寻址困难，但插入和删除容易；**所以我们将数组和链表结合在一起，发挥两者各自的优势，使用一种叫做**链地址法**的方式可以解决哈希冲突：



[![img](https://cdn.jsdelivr.net/gh/wmyskxz/img/img/Java%E9%9B%86%E5%90%88%E5%BF%85%E4%BC%9A14%E9%97%AE%EF%BC%88%E7%B2%BE%E9%80%89%E9%9D%A2%E8%AF%95%E9%A2%98%E6%95%B4%E7%90%86%EF%BC%89/7896890-c66a4bd8907f49f6.png)](https://cdn.jsdelivr.net/gh/wmyskxz/img/img/Java集合必会14问（精选面试题整理）/7896890-c66a4bd8907f49f6.png)



这样我们就可以将拥有相同哈希值的对象组织成一个链表放在hash值所对应的bucket下，**但相比于hashCode返回的int类型，我们HashMap初始的容量大小`DEFAULT_INITIAL_CAPACITY = 1 << 4`（即2的四次方16）要远小于int类型的范围，所以我们如果只是单纯的用hashCode取余来获取对应的bucket这将会大大增加哈希碰撞的概率，并且最坏情况下还会将HashMap变成一个单链表，**所以我们还需要对hashCode作一定的优化

### hash()函数

上面提到的问题，主要是因为如果使用hashCode取余，那么相当于**参与运算的只有hashCode的低位**，高位是没有起到任何作用的，所以我们的思路就是让hashCode取值出的高位也参与运算，进一步降低hash碰撞的概率，使得数据分布更平均，我们把这样的操作称为**扰动**，在**JDK 1.8**中的hash()函数如下：

```java
COPYstatic final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);// 与自己右移16位进行异或运算（高低位异或）
}
```

这比在**JDK 1.7**中，更为简洁，**相比在1.7中的4次位运算，5次异或运算（9次扰动），在1.8中，只进行了1次位运算和1次异或运算（2次扰动）；**

### JDK1.8新增红黑树



[![img](https://cdn.jsdelivr.net/gh/wmyskxz/img/img/Java%E9%9B%86%E5%90%88%E5%BF%85%E4%BC%9A14%E9%97%AE%EF%BC%88%E7%B2%BE%E9%80%89%E9%9D%A2%E8%AF%95%E9%A2%98%E6%95%B4%E7%90%86%EF%BC%89/7896890-798f1387e3f8de9a.png)](https://cdn.jsdelivr.net/gh/wmyskxz/img/img/Java集合必会14问（精选面试题整理）/7896890-798f1387e3f8de9a.png)



通过上面的**链地址法（使用散列表）**和**扰动函数**我们成功让我们的数据分布更平均，哈希碰撞减少，但是当我们的HashMap中存在大量数据时，加入我们某个bucket下对应的链表有n个元素，那么遍历时间复杂度就为O(n)，为了针对这个问题，JDK1.8在HashMap中新增了红黑树的数据结构，进一步使得遍历复杂度降低至O(logn)；

### 总结

简单总结一下HashMap是使用了哪些方法来有效解决哈希冲突的：

**1. 使用链地址法（使用散列表）来链接拥有相同hash值的数据；**
**2. 使用2次扰动函数（hash函数）来降低哈希冲突的概率，使得数据分布更平均；**

​	将 hash 的值与本身值>>16右移 进行异或运算  高低位（也是为什么table 表长度是 2^n原因）

**3. 引入红黑树进一步降低遍历的时间复杂度，使得遍历更快；**

------

## 6）HashMap为什么不直接使用hashCode()处理后的哈希值直接作为table的下标？

答：`hashCode()`方法返回的是int整数类型，其范围为-(2 ^ 31)~~(2 ^ 31 - 1)，约有40亿个映射空间，而HashMap的容量范围是在16（初始化默认值）~~2 ^ 30，HashMap通常情况下是取不到最大值的，并且设备上也难以提供这么多的存储空间，从而导致通过`hashCode()`计算出的哈希值可能不在数组大小范围内，进而无法匹配存储位置；

**面试官：那怎么解决呢？**

答：

1. HashMap自己实现了自己的`hash()`方法，通过两次扰动使得它自己的哈希值高低位自行进行异或运算，降低哈希碰撞概率也使得数据分布更平均；
2. 在保证数组长度为2的幂次方的时候，使用`hash()`运算之后的值与运算（&）（数组长度 - 1）来获取数组下标的方式进行存储，这样一来是比取余操作更加有效率，二来也是因为只有当数组长度为2的幂次方时，h&(length-1)才等价于h%length，三来解决了“哈希值与数组大小范围不匹配”的问题；

**面试官：为什么数组长度要保证为2的幂次方呢？**

答：

1. 只有当数组长度为2的幂次方时，h&(length-1)才等价于h%length，即实现了key的定位，2的幂次方也可以减少冲突次数，提高HashMap的查询效率；
2. 如果 length 为 2 的次幂 则 length-1 转化为二进制必定是 11111……的形式，在于 h 的二进制与操作效率会非常的快，而且空间不浪费；如果 length 不是 2 的次幂，比如 length 为 15，则 length - 1 为 14，对应的二进制为 1110，在于 h 与操作，最后一位都为 0 ，而 0001，0011，0101，1001，1011，0111，1101 这几个位置永远都不能存放元素了，空间浪费相当大，更糟的是这种情况中，数组可以使用的位置比数组长度小了很多，这意味着进一步增加了碰撞的几率，减慢了查询的效率！这样就会造成空间的浪费。

**面试官：那为什么是两次扰动呢？**

答：这样就是加大哈希值低位的随机性，使得分布更均匀，从而提高对应数组存储下标位置的随机性&均匀性，最终减少Hash冲突，两次就够了，已经达到了高位低位同时参与运算的目的；

------

## 7）HashMap在JDK1.7和JDK1.8中有哪些不同？

答：

| 不同                     | JDK 1.7                                                      | JDK 1.8                                                      |
| ------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 存储结构                 | 数组 + 链表                                                  | 数组 + 链表 + 红黑树                                         |
| 初始化方式               | 单独函数：`inflateTable()`                                   | 直接集成到了扩容函数`resize()`中                             |
| hash值计算方式           | 扰动处理 = 9次扰动 = 4次位运算 + 5次异或运算                 | 扰动处理 = 2次扰动 = 1次位运算 + 1次异或运算                 |
| 存放数据的规则           | 无冲突时，存放数组；冲突时，存放链表                         | 无冲突时，存放数组；冲突 & 链表长度 < 8：存放单链表；冲突 & 链表长度 > 8：树化并存放红黑树 |
| 插入数据方式             | 头插法（先讲原位置的数据移到后1位，再插入数据到该位置）      | 尾插法（直接插入到链表尾部/红黑树）                          |
| 扩容后存储位置的计算方式 | 全部按照原来方法进行计算（即hashCode ->> 扰动函数 ->> (h&length-1)） | 按照扩容后的规律计算（即扩容后的位置=原位置 or 原位置 + 旧容量） |



------

## 8）为什么HashMap中String、Integer这样的包装类适合作为K？

答：String、Integer等包装类的特性能够保证Hash值的不可更改性和计算准确性，能够有效的减少Hash碰撞的几率

1. 都是final类型，即不可变性，保证key的不可更改性，不会存在获取hash值不同的情况
2. 内部已重写了`equals()`、`hashCode()`等方法，遵守了HashMap内部的规范（不清楚可以去上面看看putValue的过程），不容易出现Hash值计算错误的情况；

**面试官：如果我想要让自己的Object作为K应该怎么办呢？**

答：重写`hashCode()`和`equals()`方法

1. **重写`hashCode()`是因为需要计算存储数据的存储位置**，需要注意不要试图从散列码计算中排除掉一个对象的关键部分来提高性能，这样虽然能更快但可能会导致更多的Hash碰撞；
2. **重写`equals()`方法**，需要遵守自反性、对称性、传递性、一致性以及对于任何非null的引用值x，x.equals(null)必须返回false的这几个特性，**目的是为了保证key在哈希表中的唯一性**；

------

## 9）ConcurrentHashMap和Hashtable的区别？

答：ConcurrentHashMap 结合了 HashMap 和 HashTable 二者的优势。HashMap 没有考虑同步，HashTable 考虑了同步的问题。但是 HashTable 在每次同步执行时都要锁住整个结构。 ConcurrentHashMap 锁的方式是稍微细粒度的。

**面试官：ConcurrentHashMap的具体实现知道吗？**

> 参考资料：http://www.importnew.com/23610.html

答：**在JDK1.7中，ConcurrentHashMap采用Segment + HashEntry的方式进行实现**，结构如下：



[![img](https://cdn.jsdelivr.net/gh/wmyskxz/img/img/Java%E9%9B%86%E5%90%88%E5%BF%85%E4%BC%9A14%E9%97%AE%EF%BC%88%E7%B2%BE%E9%80%89%E9%9D%A2%E8%AF%95%E9%A2%98%E6%95%B4%E7%90%86%EF%BC%89/7896890-645836e722c2a9f9.png)](https://cdn.jsdelivr.net/gh/wmyskxz/img/img/Java集合必会14问（精选面试题整理）/7896890-645836e722c2a9f9.png)



1. 该类包含两个静态内部类 HashEntry 和 Segment ；前者用来封装映射表的键值对，后者用来充当锁的角色；
2. Segment 是一种可重入的锁 ReentrantLock，每个 Segment 守护一个HashEntry 数组里得元素，当对 HashEntry 数组的数据进行修改时，必须首先获得对应的 Segment 锁。

在**JDK1.8中，放弃了Segment臃肿的设计，取而代之的是采用Node + CAS + Synchronized来保证并发安全进行实现**，结构如下：



[![img](https://cdn.jsdelivr.net/gh/wmyskxz/img/img/Java%E9%9B%86%E5%90%88%E5%BF%85%E4%BC%9A14%E9%97%AE%EF%BC%88%E7%B2%BE%E9%80%89%E9%9D%A2%E8%AF%95%E9%A2%98%E6%95%B4%E7%90%86%EF%BC%89/7896890-ec8560395e25547a.png)](https://cdn.jsdelivr.net/gh/wmyskxz/img/img/Java集合必会14问（精选面试题整理）/7896890-ec8560395e25547a.png)



插入元素过程（建议去看看源码）：

1. 如果相应位置的Node还没有初始化，则调用CAS插入相应的数据；

```java
COPYelse if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
    if (casTabAt(tab, i, null, new Node<K,V>(hash, key, value, null)))
        break;                   // no lock when adding to empty bin
}
```

1. 如果相应位置的Node不为空，且当前该节点不处于移动状态，则对该节点加synchronized锁，如果该节点的hash不小于0，则遍历链表更新节点或插入新节点；

```java
COPYif (fh >= 0) {
    binCount = 1;
    for (Node<K,V> e = f;; ++binCount) {
        K ek;
        if (e.hash == hash &&
            ((ek = e.key) == key ||
             (ek != null && key.equals(ek)))) {
            oldVal = e.val;
            if (!onlyIfAbsent)
                e.val = value;
            break;
        }
        Node<K,V> pred = e;
        if ((e = e.next) == null) {
            pred.next = new Node<K,V>(hash, key, value, null);
            break;
        }
    }
}
```

1. 如果该节点是TreeBin类型的节点，说明是红黑树结构，则通过putTreeVal方法往红黑树中插入节点；如果binCount不为0，说明put操作对数据产生了影响，如果当前链表的个数达到8个，则通过treeifyBin方法转化为红黑树，如果oldVal不为空，说明是一次更新操作，没有对元素个数产生影响，则直接返回旧值；
2. 如果插入的是一个新节点，则执行addCount()方法尝试更新元素个数baseCount；

------

## 10）Java集合的快速失败机制 “fail-fast”？

答：

**是java集合的一种错误检测机制，当多个线程对集合进行结构上的改变的操作时，有可能会产生 fail-fast 机制。**

例如：假设存在两个线程（线程1、线程2），线程1通过Iterator在遍历集合A中的元素，在某个时候线程2修改了集合A的结构（是结构上面的修改，而不是简单的修改集合元素的内容），那么这个时候程序就会抛出 ConcurrentModificationException 异常，从而产生fail-fast机制。

**原因：迭代器在遍历时直接访问集合中的内容，并且在遍历过程中使用一个 modCount 变量。集合在被遍历期间如果内容发生变化，就会改变modCount的值。每当迭代器使用hashNext()/next()遍历下一个元素之前，都会检测modCount变量是否为expectedmodCount值，是的话就返回遍历；否则抛出异常，终止遍历。**

**解决办法：**

**1. 在遍历过程中，所有涉及到改变modCount值得地方全部加上synchronized。**

**2. 使用CopyOnWriteArrayList来替换ArrayList**

------

## 11）ArrayList 和 Vector 的区别？

答：

这两个类都实现了 List 接口（List 接口继承了 Collection 接口），他们都是有序集合，即存储在这两个集合中的元素位置都是有顺序的，相当于一种动态的数组，我们以后可以按位置索引来取出某个元素，并且其中的数据是允许重复的，这是与 HashSet 之类的集合的最大不同处，HashSet 之类的集合不可以按索引号去检索其中的元素，也不允许有重复的元素。

ArrayList 与 Vector 的区别主要包括两个方面：

1. 同步性：
   Vector 是线程安全的，也就是说它的方法之间是线程同步（加了synchronized 关键字）的，而 ArrayList 是线程不安全的，它的方法之间是线程不同步的。如果只有一个线程会访问到集合，那最好是使用 ArrayList，因为它不考虑线程安全的问题，所以效率会高一些；如果有多个线程会访问到集合，那最好是使用 Vector，因为不需要我们自己再去考虑和编写线程安全的代码。
2. 数据增长：
   ArrayList 与 Vector 都有一个初始的容量大小，当存储进它们里面的元素的个人超过了容量时，就需要增加 ArrayList 和 Vector 的存储空间，每次要增加存储空间时，不是只增加一个存储单元，而是增加多个存储单元，每次增加的存储单元的个数在内存空间利用与程序效率之间要去的一定的平衡。Vector 在数据满时（加载因子1）增长为原来的两倍（扩容增量：原容量的 2 倍），而 ArrayList 在数据量达到容量的一半时（加载因子 0.5）增长为原容量的 (0.5 倍 + 1) 个空间。

------

## 12）ArrayList和LinkedList的区别？

答：

1. LinkedList 实现了 List 和 Deque 接口，一般称为双向链表；ArrayList 实现了 List 接口，动态数组；
2. LinkedList 在插入和删除数据时效率更高，ArrayList 在查找某个 index 的数据时效率更高；
3. LinkedList 比 ArrayList 需要更多的内存；

**面试官：Array 和 ArrayList 有什么区别？什么时候该应 Array 而不是 ArrayList 呢？**

答：它们的区别是：

1. Array 可以包含基本类型和对象类型，ArrayList 只能包含对象类型。
2. Array 大小是固定的，ArrayList 的大小是动态变化的。
3. ArrayList 提供了更多的方法和特性，比如：addAll()，removeAll()，iterator() 等等。

对于基本类型数据，集合使用自动装箱来减少编码工作量。但是，当处理固定大小的基本数据类型的时候，这种方式相对比较慢。

------

## 13）HashSet是如何保证数据不可重复的？

答：HashSet的底层其实就是HashMap，只不过我们**HashSet是实现了Set接口并且把数据作为K值，而V值一直使用一个相同的虚值来保存**，我们可以看到源码：

```java
COPYpublic boolean add(E e) {
    return map.put(e, PRESENT)==null;// 调用HashMap的put方法,PRESENT是一个至始至终都相同的虚值
}
```

由于HashMap的K值本身就不允许重复，并且在HashMap中如果K/V相同时，会用新的V覆盖掉旧的V，然后返回旧的V，那么在HashSet中执行这一句话始终会返回一个false，导致插入失败，这样就保证了数据的不可重复性；

------

## 14）BlockingQueue是什么？

答：

Java.util.concurrent.BlockingQueue是一个队列，在进行检索或移除一个元素的时候，它会等待队列变为非空；当在添加一个元素时，它会等待队列中的可用空间。BlockingQueue接口是Java集合框架的一部分，主要用于实现生产者-消费者模式。我们不需要担心等待生产者有可用的空间，或消费者有可用的对象，因为它都在BlockingQueue的实现类中被处理了。Java提供了集中BlockingQueue的实现，比如ArrayBlockingQueue、LinkedBlockingQueue、PriorityBlockingQueue,、SynchronousQueue等。









