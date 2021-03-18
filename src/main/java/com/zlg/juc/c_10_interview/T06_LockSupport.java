package com.zlg.juc.c_10_interview;

import java.util.ArrayList;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport实现
 * park unpark 替代 wait notify
 */
public class T06_LockSupport {
  public ArrayList<Object> list = new ArrayList<>();

  public void add(Object o) {
    list.add(o);
  }

  public int size() {
    return list.size();
  }

  Thread t2 = null;
  public static void main(String[] args) {
    T06_LockSupport t = new T06_LockSupport();
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        t.add(new Object());
        System.out.println("add"+i);
        if (t.size() == 5) {
          LockSupport.unpark(t.t2);
          LockSupport.park();
        }
      }
    });

    t.t2 = new Thread(() -> {
      LockSupport.park();
      LockSupport.unpark(t1);
      System.out.println("t2 已结束");
    });

    t.t2.start();
    t1.start();
  }
}
