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
