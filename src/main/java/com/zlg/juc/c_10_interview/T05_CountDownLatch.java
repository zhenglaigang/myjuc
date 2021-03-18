package com.zlg.juc.c_10_interview;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch做一个或多个线程的同步
 * 使用 await countDown 替代 wait notify
 * 当不涉及同步，只是涉及线程通信的时候，用synchronized + wait/notify就显得太重了，
 * 应该考虑 CountDownLatch/LockSupport/Semaphore
 */
public class T05_CountDownLatch {
  public ArrayList<Object> list = new ArrayList<>();

  public void add(Object o) {
    list.add(o);
  }

  public int size() {
    return list.size();
  }

  public static void main(String[] args) {
    CountDownLatch latch1 = new CountDownLatch(1);
    CountDownLatch latch2 = new CountDownLatch(1);
    T05_CountDownLatch t = new T05_CountDownLatch();

    new Thread(() -> {
      System.out.println("t2 启动");
      try {
        latch1.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      latch2.countDown();
      System.out.println("t2 结束");
    }).start();

    new Thread(() -> {
      System.out.println("t1 启动");
      for (int i = 0; i < 10; i++) {
        t.add(new Object());
        System.out.println("add"+i);
        if (t.size() == 5) {
          latch1.countDown();
          try {
            latch2.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    },"t1").start();
  }

}
