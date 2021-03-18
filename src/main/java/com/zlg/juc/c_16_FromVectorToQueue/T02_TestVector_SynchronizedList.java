package com.zlg.juc.c_16_FromVectorToQueue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 模拟10个窗口卖火车票
 * <p>
 * ArrayList 不同步，多线程会出现超卖现象
 * 那用 Vector SynchronizedList能不能解决呢？
 *
 * 容器换用 Vector SynchronizedList 并不能解决超卖问题
 * 因为容器里面的方法是原子性操作，但容器A B两个原子操作中间可能会有其他的非原子操作
 */
public class T02_TestVector_SynchronizedList {
  //  static Vector<String> tickets = new Vector<>();
  static List<String> tickets = Collections.synchronizedList(new LinkedList<>());

  static {
    for (int i = 0; i < 1000; i++) {
      tickets.add("火车票" + i);
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        while (tickets.size() > 0) {
          try {
            TimeUnit.MILLISECONDS.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          System.out.println("售卖了" + tickets.remove(0));
        }
      }).start();
    }
  }
}
