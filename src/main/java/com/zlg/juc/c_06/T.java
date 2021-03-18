package com.zlg.juc.c_06;

import java.util.concurrent.TimeUnit;

/**
 * 发生异常，会释放锁
 */
public class T {
  private int count = 0;
  public void m() {
    synchronized (this) {
      while (true) {
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        count++;
        System.out.println(Thread.currentThread().getName() + "count=" + count);
        if (count == 5) {
          int x = 1 / 0;
        }
      }
    }
  }

  //t1线程自增到5后，发生异常释放锁；t2线程获得锁开始执行
  public static void main(String[] args) {
    T t = new T();
    new Thread(t::m, "t1").start();
    new Thread(t::m, "t2").start();
  }
}
