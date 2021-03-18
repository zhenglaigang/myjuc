package com.zlg.juc.c_09;

import java.util.concurrent.CyclicBarrier;

public class T07_CyclicBarrier {
  //static CyclicBarrier barrier = new CyclicBarrier(20);
  static CyclicBarrier barrier = new CyclicBarrier(20, () -> {
    System.out.println("人满发车");
  });
  public static void main(String[] args) {
    Thread[] threads = new Thread[100];
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(() -> m());
    }
    for (Thread thread : threads) thread.start();
  }

  public static void m() {
    try {
      int await = barrier.await();//执行一次await，判断执行当前代码的线程数
      if (await == 19)System.out.println("await线程数：" + (await+1));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
