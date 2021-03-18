package com.zlg.juc.c_02;

/**
 * synchronized 关键字
 * 对对象加锁
 */
public class T {
  Object o = new Object();
  private int count = 10;
  public void m() {

    synchronized(o) {
      count--;
      System.out.println(Thread.currentThread().getName() + "count=" + count);
    }
  }

}
