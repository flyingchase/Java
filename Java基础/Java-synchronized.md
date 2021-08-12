# Java-synchronized

## 概念

​	是 Java 内建的同步机制 线程对`共享资源`加锁后其他想要获取的线程必须等待，具有互斥和排他性



## 实例

- `synchronized`修饰实例方法

  对类的实例进行加锁 进入同步代码前需要获得当前实例的锁

  ``` java
  // public synchronized void method() {...}
  
  package Synchronized;
      // synchronized 修饰的方法具有独占性
  public class TSynochronized implements  Runnable{
  
      @Override
      public void run() {
          for (int i = 0; i < 1000; i++) {
              increase();
          }
      }
      // i 为静态变量 存放在方法区内
      static int i=0;
      // increase 方法由 synchronized 修饰但无 static 关键字修饰
      // increase 为实例方法 每创建的 TSynchronized类军会同市创建 increase 方法
      public synchronized void increase() {
          i++;
          // 打印当前线程名称
          System.out.println(Thread.currentThread().getName());
      }
  
      public static void main(String[] args) throws InterruptedException{
          TSynochronized tSynochronized = new TSynochronized();
          Thread aThread = new Thread(tSynochronized);
          Thread bThread = new Thread(tSynochronized);
  
          aThread.start();
          bThread.start();
          aThread.join();
          bThread.join();
          System.out.println("i = " + i);
      }
  }
  
  
  ```

  

- 修饰静态方法

  给类的对象加锁 作用于静态方法*static*修饰 静态方法属于类 及 synchronized 表示当前类的锁 不属于任何一个实例成员 可以通过`class`对象控制并发访问

  ``` java
  // public static synchronized void increase()
  
  ```

  

- 修饰代码开

  给对象加锁  

  ``` java
  public void run() {
  	// 对 obj 对象加锁 要求访问线程必须持有 obj实例对象锁
      synchronized(obj);
      for(int i=0;i<1000;i++) {
          i++;
      }
  }
  // 实例对象锁
  synchronized(this) {
      for(int i=0;i<1000;i++) {
          i++;
      }
  }
  // class 对象锁
  synchronized(TSynchronized.class) {
      for(int i=0;i<1000;i++) {
          i++;
      }
  }
  
  ```

  

## 底层原理

是由 Monitor相关指令实现的 `Monitor` 对象是实现同步的基本单元















## 参考

[程序员 cxuan-synchronized 超多干货](https://mp.weixin.qq.com/s?__biz=MzI0ODk2NDIyMQ==&mid=2247491370&idx=1&sn=ce05241e534360e8872b8d4d70ca3807&chksm=e999ea38deee632edf4d778dceccbd407ffc3f412d19a74a3ee4b96158d49f8f1ec1835fa4d5&scene=0&xtrack=1#rd)









































、、 