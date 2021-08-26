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





## String 

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



- **替换/循环移动**

  - 使用 charAt(index)和 subSrting(index)（index 和后直到字符串结尾的整个子串）循环移动子串

- **split**

  - String[] line =  str.split(“ ”) //以空格划分
  - Sting s =line[1].split(“\\\\.”)  // 以.划分

- **子串后缀链接**

  - ``` java
    String temp  ="";
    // 从后往前遍历 sting 
    for(int i=domains.leng-1;i>=0;i--) {
        // 保证子串不断增长
        temp = domains[i] + (temp.equals("")?temp:"."+temp);
    }
    ```

- **遍历中使用subString获得后续子串**

  - ``` java
    for (String cd : cpdomains) {
    	// ues indexOf 确定''划分的位置下标
        int i = cd.indexOf(' ');
        // ues subString 获得空格前的数值 Integer.valueOf 转化
        int n = Integer.valueOf(cd.substring(0, i));
        
        String s = cd.substring(i + 1);
        for (i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '.') {
                // 获得'.'划分的子缀 正向遍历不丢失后续信息
                String d = s.substring(i + 1);
                count.put(d, count.getOrDefault(d, 0) + n);
            }
        }
    ```

- 

- **筛选：**

  - LC811

### Tips

#### 替换字符

##### 思路

- 使用 `HashSet`保存替换的字典集  Arrays.asList(‘...’)作为参数 保存为 private static final 类型

  ```java
  private static final HashSet<Character> vowels = new HashSet<>(
          Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
  ```

- 使用 `s.split(“ ”)`删除空格字符
- 使用 `StringBuilder`作为append的新字符串 最后 toString返回



##### Codes

```java
private static final HashSet<Character> vowels = new HashSet<>(
        Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

public String toGoatLatin(String S) {
    if (S == null || S.length() == 0) {
        return "";
    }

    StringBuilder sb = new StringBuilder();
    StringBuilder suffix = new StringBuilder("a");

    for (String w : S.split(" ")) {
        // 保证前导“ ”在非第一个字符前出现
        if (sb.length() != 0) {
            sb.append(" ");
        }
		// 暂存 w 的首字符 判断是否是元音
        char fChar = w.charAt(0);
        if (vowels.contains(fChar)) {
            sb.append(w);
        } else {
            // 使用 subString 链接除首字符外的字符串
            sb.append(w.substring(1));
            // 再将暂存的首字符追加
            sb.append(fChar);
        }
		// 连续追加
        sb.append("ma").append(suffix);
		// 保证后缀a 随字符的下标增加
        suffix.append("a");
    }
    // System.out.println("sb.toString() = " + sb.toString());
    return sb.toString();
}
```



- 



### sb 

- **连续追加**

  - `Sb.append(“aa”).append(“string”)`

- **构建二维 string**

  - ```java
    StringBuffer[] sb = new StringBuffer[len];
    for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();
    ```











## Array 数组

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
**bsearch:** `Arrays.binarySearch()`   must be sorted

```java

int ind = Arrays.binarySearch(students, 0,N,student);//类中需要重构equals函数和compareTo函数


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



### Tips

#### 右移数组

思路： k%=n;保证 k在数组长度 n 之内

``` java
reverse(nums,0,n-k-1);
reverse(nums,n-k,n-1);
reverse(nums,0,n-1);
```



#### O(1)删除重复元素

思路：从j=0 i=0开始 i 遍历数组  当两指针指向不相等时候++j; A[j]=A[i];

```java
public int removeDuplicates(int[] A) {
    // return the length of A
    if (A.length==0) return 0;
    int j=0;
    // 不断与下一个元素比较 不同则替换为下一元素
    for (int i=0; i<A.length; i++)
        if (A[i]!=A[j]) A[++j]=A[i];
    return ++j;
}
```



#### 找到升序的下一个稍大排列

思路：

- 从后向前遍历数组 找到非升序的转折点

- 从转折点向后找 找到转折点后最小的数字 并交换转折点和其后的最小位置  即为下一个稍大的排列



```java
public int[] nextPermutation(int[] nums) {
    int len = nums.length;
    if ( len <= 1)
        return nums;
    int i = len - 1; 
    // 从后向前找到非递增的转折点
    while (i > 0 && nums[i] <= nums[i - 1])
        i --;
    // 区间逆转 将转折点及后续部分逆转
    swapList(nums, i, len - 1);
    if (i != 0) {
        int j = i;
        while (nums[j] <= nums[i - 1]) j++;
        // 找到转折点后稍大的数字并交换位置
        swapItem(nums, j, i-1);
    }
    return nums;
}
```

全部代码：

```java
public void nextPermutation(int[] A) {
    if(A == null || A.length <= 1) return;
    int i = A.length - 2;
    while(i >= 0 && A[i] >= A[i + 1]) i--; // Find 1st id i that breaks descending order
    if(i >= 0) {                           // If not entirely descending
        int j = A.length - 1;              // Start from the end
        while(A[j] <= A[i]) j--;           // Find rightmost first larger id j
        swap(A, i, j);                     // Switch i and j
    }
    reverse(A, i + 1, A.length - 1);       // Reverse the descending sequence
}

public void swap(int[] A, int i, int j) {
    int tmp = A[i];
    A[i] = A[j];
    A[j] = tmp;
}

public void reverse(int[] A, int i, int j) {
    while(i < j) swap(A, i++, j--);
}
```



