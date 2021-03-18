package com.zlg.juc.c_09;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 做一个或多个线程的同步，用于等待线程结束
 * CountDownLatch vs Join
 * Join只能等待线程自己执行结束
 * CountDownLatch可以通过执行计数方法，手动让线程结束
 */
public class T06_CountDownLatch {

  public static void main(String[] args) throws InterruptedException {
    useJoin();
    useCountDownLatch();
  }

  public static void useCountDownLatch() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(100);
//    Thread[] threads = new Thread[100];
    Thread[] threads = new Thread[99];
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(() -> {
        int result = 0;
        for (int k = 0; k < 10000; k++) result++;
      });
      latch.countDown();
    }
    for (Thread thread : threads) thread.start();

    latch.countDown();//如果只有99个线程，可以手动执行一次countDown，结束等待
    latch.await();
    System.out.println("CountDownLatch end");
  }

  public static void useJoin() {
    Thread[] threads = new Thread[100];
    for (int i = 0; i < threads.length; i++) {
      Thread thread = new Thread(() -> {
        int result = 0;
        for (int k = 0; k < 10000; k++) {
          result++;
        }
      });
      threads[i] = thread;
    }

    for (Thread thread : threads) {
      thread.start();
    }

    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.println("join end");
  }
}
