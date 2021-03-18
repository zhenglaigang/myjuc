package com.zlg.juc.c_16_FromVectorToQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T04_TestQueue {
  static Queue<String> tickets = new ConcurrentLinkedQueue<>();

  static {
    for (int i = 0; i < 1000; i++) {
      tickets.add("火车票" + i);
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        while (true) {
          String s = tickets.poll();
          if (s == null) break;
          System.out.println("售卖了" + s);
        }
      }).start();
    }
  }
}
