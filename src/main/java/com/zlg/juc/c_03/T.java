package com.zlg.juc.c_03;

/**
 * synchronized 保证new 的100线程 同步执行count--
 */
public class T implements Runnable {
  private int count = 100;
  @Override
  public synchronized void run() {
    count--;
    System.out.println(Thread.currentThread().getName() + "count=" + count);
  }

  public static void main(String[] args) {
    T t = new T();
    for (int i=0; i<100; i++) {
      new Thread(t, "Thread"+i).start();
    }
  }
}
