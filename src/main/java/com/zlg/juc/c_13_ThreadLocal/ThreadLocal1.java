package com.zlg.juc.c_13_ThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * 局部变量
 */
public class ThreadLocal1 {
  static volatile Person p = new Person();
  public static void main(String[] args) {

    new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(p.name);
    }, "t1").start();

    new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      p.name = "lisi";
    }).start();
  }
}
