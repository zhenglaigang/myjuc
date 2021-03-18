package com.zlg.juc.c_16_FromVectorToQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟10个窗口卖火车票
 *
 * ArrayList 不同步，多线程会出现超卖现象
 * 那用 Vector SynchronizedList能不能解决呢？
 */
public class T01_TestArrayList {
  static List<String> tickets = new ArrayList<>();
  static {
    for (int i = 0; i < 10000; i++) {
      tickets.add("火车票" + i);
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        while (true) {
          if (tickets.size() > 0) {
            System.out.println("售卖了" + tickets.remove(0));
          }
        }
      }).start();
    }
  }
}
