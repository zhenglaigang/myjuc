package com.zlg.juc.c_04;

import java.util.concurrent.TimeUnit;

/**
 * 验证锁的可重入性
 * 一个同步方法可以调用另一个同步方法，获取到相同的锁
 */
public class T01_SynchronizedReentry {
  public synchronized static void m() {
    System.out.println("m start");
    try {
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    n();
    System.out.println("m end");
  }

  public synchronized static void n() {
    System.out.println("n start");
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("n end");
  }

  public static void main(String[] args) {
    //一个线程执行m() , 另一个线程无法同时执行n() ,因为锁的是同一个对象无法同时获取锁
    /*new Thread(T01_SynchronizedReentry::m).start();
    new Thread(T01_SynchronizedReentry::n).start();*/

    //m() 里调用 n() ,可以重入
    m();
  }
}
