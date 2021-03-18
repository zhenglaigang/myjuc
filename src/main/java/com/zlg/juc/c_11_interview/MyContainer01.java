package com.zlg.juc.c_11_interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Synchronized wait notifyAll 同步容器
 *
 */
public class MyContainer01<T> {
  private final int MAX = 10;
  private int count = 0;
  LinkedList<T> list = new LinkedList<>();
  public synchronized void put(T t) {
    while (list.size() == MAX) {
      //已满阻塞写操作
      try {
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    list.add(t);
    ++count;
    notifyAll();//通知所有的消费线程
  }

  public synchronized T get() {
    while (count == 0) {
      try {
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    T t = list.removeFirst();
    count--;
    notifyAll();//通知所有生产线程
    return t;
  }

  public static void main(String[] args) {
    MyContainer01<Object> c = new MyContainer01<>();

    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        for (int j = 0; j < 5; j++) {
          System.out.println(c.get());
        }
      }, "c" + i).start();
    }

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < 2; i++) {
      new Thread(() -> {
        for (int j = 0; j < 25; j++) {
          c.put(Thread.currentThread().getName() + "_" + j);
        }
      }, "p" + i).start();
    }

  }
}
