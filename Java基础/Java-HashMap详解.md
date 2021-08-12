Java-HashMap详解

[美团技术团队-重新认识HashMap](https://zhuanlan.zhihu.com/p/21673805)



## 0 前置

<img src="https://cdn.jsdelivr.net/gh/flyingchase/Private-Img@master/uPic/GHz4Tn.png" alt="GHz4Tn" style="zoom:40%;" />

- `Node[] table：哈希桶数组` Node 是 HashMap 的一个内部类。实现 Map.Entry 接口，本质上是一个键值对

- HashMap使用哈希表存储：通过链地址法来解决冲突

  数组+链表（链地址法）：通过 Key 的`hashCode()`方法得到 hashCode 值通过 Hash 算法的后两步运算`高位运算、取模运算`来定位键值对的存储位置。

  **Hash 碰撞：**两个 Key 定位在相同的位置







- 构造函数初始化：

  ``` java
  int threshold;			//K-V 对的极限
  final float loadFactor;	//负载因子
  int modCount;	// HashMap 内部结构变化的次数 更新 value 值不属于结构变化
  int size;		// 实际存在的键值对数量
  ```

  `threshold=lodFactor*length` 超过则<<1 两倍扩容

















