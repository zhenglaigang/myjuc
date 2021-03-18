package com.zlg.juc.c_13_ThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal以空间换时间，Synchronized是以时间换空间
 */
public class ThreadLocal2 {
  //Person p = new Person();
  static ThreadLocal<Person> threadLocal = new ThreadLocal<>();
  public static void main(String[] args) {
    new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(threadLocal.get());
    }).start();

    new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      threadLocal.set(new Person());
    }).start();
  }
}
