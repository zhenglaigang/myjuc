package com.zlg.juc.c_05;

import java.util.concurrent.TimeUnit;

/**
 * 同一个对象的同步方法和不同步方法是否可以同时被调用
 */
public class T {
  public synchronized void m() {
    System.out.println("m start");
    try {
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("m end");
  }

  public void n() {
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("n");
  }

  public static void main(String[] args) {
    T t = new T();
    new Thread(t::m, t.getClass().getName()).start();
    new Thread(t::n, t.getClass().getName()).start();
  }
}
