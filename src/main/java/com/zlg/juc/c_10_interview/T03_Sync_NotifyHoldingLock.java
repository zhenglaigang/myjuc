package com.zlg.juc.c_10_interview;

/**
 * synchronized 同步 notify 通知 -只通知不释放锁
 * t1线程 notify只通知，并不会释放锁，t1执行后才释放锁
 * t2线程被唤醒后，获取不到锁，等待执行
 * 结果：t1执行完后，t2才执行，无法满足要求
 */

import java.util.LinkedList;
import java.util.List;

public class T03_Sync_NotifyHoldingLock {
  volatile List<Object> list=new LinkedList<>();
  public void add(Object o) {
    list.add(o);
  }

  public int size() {
    return list.size();
  }

  static Object lock = new Object();
  public static void main(String[] args) {
    T03_Sync_NotifyHoldingLock t = new T03_Sync_NotifyHoldingLock();
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        synchronized (lock) {
          t.add(new Object());
          System.out.println("add" + i);
          if (t.size() == 5) {
            lock.notify();
          }
        }
      }
    });

    Thread t2 = new Thread(() -> {
      synchronized (lock) {
        try {
          lock.wait();
          System.out.println("t2 已结束");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    t2.start();//t2需先启动，wait阻塞住
    t1.start();
  }
}
