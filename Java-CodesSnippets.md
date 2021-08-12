# Java代码片段

## IO 输入输出

``` java
package Review;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.PriorityQueue;
import java.util.Scanner;

public class JavaCodesSnippetsDemo {

    public static void main(String args[]) {
        Scanner cin = new Scanner(new BufferedInputStream(System.in));
// 2. 读入一个整数
        int n = cin.nextInt();
// 3. 读入一个字符串
        String s = cin.next();
// 4. 读入一个浮点数
        double d = cin.nextDouble();
// 5. 读入一行
        String s1 = cin.nextLine();
        // 如果把next（）或者nextInt（），nextDouble() 、 nextFloat()用在nextLine的前面时。nextLine会把前者的结束符“换行符”作为字符串读入，进而不需要从键盘输入字符串nextLine已经转向下一条语句执行
        // 修正方法：在next()或nextInt()方法使用Enter键之后，填充一个无用的nextLine()
// 6. 输出
        System.out.println();
        System.out.printf("%d %10.5f\n", a, b);
// 7. 浮点数保留几位小数，DecimalFormat类
//     0 表示如果位数不足则以 0 填充，# 表示只要有可能就把数字拉上这个位置。这里0指一位数字，#指除0以外的数字。
        DecimalFormat f = new DecimalFormat("#.##%");
        DecimalFormat f = new DecimalFormat(",###.##");
        System.out.println(f.format(d));
// 8. 大数BigInteger BigDecimal
//     存储任意精度的数，运算速度比较慢
//     add,substract,multiply,divide,remainder,compareTo()
//     divideAndRemainder：a[0]=this / val; a[1]=this % val
//     pow,gcd,abs,negate,signum,mod,shiftLeft(this<<n),shiftRight(this>>n),and,or,xor
        BigInteger bi = new BigInteger("2");
        BigInteger bi = new BigInteger("-101", 2);
        System.out.println(bi.toString(10));
        //会使用科学计数法，toPlainString()直接显示
        // toString()
        //返回数值上等于此小数，移除末尾的0的BigDecimal
        // stripTrailingZeros()
// 9. 进制转换
        String st = Integer.toString(num, base);
        int num = Integer.parseInt(st, base);


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //scanf不能过的一个例子
//        Scanner cin = new Scanner(new BufferedInputStream(System.in));
        BufferedReader cin02 = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = cin02.readLine()) != null) {
            int n = Integer.parseInt(line);

            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                line = cin02.readLine();
                String[] items = line.split(" ");
                if (Integer.parseInt(items[0]) == 1) {
                    priorityQueue.offer(Integer.parseInt(items[1]));
                } else {
                    System.out.println(priorityQueue.poll());
                }
            }
        }
    }
}

```





## String 基础操作

``` java
// 1. 基本操作
    charAt(),substring()
    ch = st.toCharArray(); // 字符串转换为字符数组.
// 2. 比较
    if(!this.name.equals(s1.name)){
         return this.name.compareTo(s1.name);
    }
// 3.StringBuilder比较
	s1.toString().equals(s2.toString())

```







## 数组

1. 相对于array，ArrayList特点：
	动态改变大小，如果事先知道大小，并且不会改变可以使用Array
	存储需要的空间更多
	编译时检查类型是否正确
	只能保存封装类
	可以通过iterator遍历
	size获取存储元素的个数,array中length获取数组长度
	不支持多维
	
2. list接口
  add(),get(int index),remove(int index),set(),clear()
  ArrayList类的特点：底层是数组结构，查询快，增删慢；线程不安全，效率高
  Vector类的特点：底层是数组机构，查询快，增删慢；线程安全，效率低
  LinkedList类的特点：底层是链表结构，增删快，查询慢；线程不安全，效率低
  removeall:比较参数collection的对象是否包含，如果包含则删除，复杂度O(n^2)
  clear：将所有元素置为空

  2. array操作
  	**memset：**`Arrays.fill()`
  	**sort:**`Arrays.sort()`
  	**bsearch:** `binarySearch()`   must be sorted
  	int ind = Arrays.binarySearch(students, 0,N,student);//类中需要重构equals函数和compareTo函数
  	
     ``` java
     public int compareTo(Student student) {
          return sid.compareTo(student.sid);
      }
      @Override
      public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Student student = (Student) o;
  	     return Objects.equals(sid, student.sid);
  	 }
  	int dir[][]={{1,0},{-1,0},{0,1},{0,-1}};
  	```
  	
  	

4. 数组反转
	`Collections.reverse(buf);`
	
4. 寻找一个集合的最大元素

  1. Arrays.sort()然后取第一个元素
  2. `Collection.max()`

5. 两种复制方法
  System.arraycopy(arr1, 0, arr2, 0, arr1.length);
  arr2 = Arrays.copyOf(arr1, arr1.length * 2);





