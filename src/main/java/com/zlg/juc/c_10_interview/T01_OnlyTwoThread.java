package com.zlg.juc.c_10_interview;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * ArrayList + OnlyTwoThread
 * 问题1：线程之间数据不可见，无法实现
 * 问题2：存在同步问题，add() 完成后会size++，不能保证原子性
 */
public class T01_OnlyTwoThread {
  public ArrayList<Object> list = new ArrayList<>();

  public void add(Object o) {
    list.add(o);
  }

  public int size() {
    return list.size();
  }

  public static void main(String[] args) {
    T01_OnlyTwoThread t = new T01_OnlyTwoThread();
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        t.add(new Object());
        System.out.println("add"+i);
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread t2 = new Thread(() -> {
      while (true) {
        if (t.size() == 5) {
          break;
        }
      }
      System.out.println("t2 已结束");
    });

    t2.start();
    t1.start();
  }
}
