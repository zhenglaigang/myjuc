package com.zlg.juc.c_09;

/**
 * 从synchronized 完成同步开始
 */
public class T01_ReentrantLock_01 {
  private int count = 100;

  public void m() {
    synchronized (this) {
      count--;
      System.out.println(Thread.currentThread().getName() + " count=" + count);
    }
  }

  public static void main(String[] args) {

    T01_ReentrantLock_01 t = new T01_ReentrantLock_01();
    for (int i=0; i<100; i++) {
      new Thread(t::m).start();
    }
  }
}
