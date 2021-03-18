package com.zlg.juc.c_09;

import java.util.concurrent.Exchanger;

/**
 * Exchanger 线程数据交换
 */
public class T11_Exchanger {
  static Exchanger<String> exchanger = new Exchanger();

  public static void main(String[] args) {
    new Thread(() -> {
      String v = "T1";
      try {
        v = exchanger.exchange(v);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "," + v);
    }, "t1").start();

    new Thread(() -> {
      String v = "T2";
      try {
        v = exchanger.exchange(v);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "," + v);
    }, "t2").start();
  }
}
