package com.zlg.juc.c_10_interview;

/**
 * synchronized 同步 notify 通知 -通知并释放锁
 * -- t2启动wait，释放锁
 * -- t1获取锁，执行 ==5时notify 通知 t2 执行；并执行wait释放锁
 * -- t2得到通知被唤醒，并可以获取到锁，得到执行并结束；执行完成notify通知t1执行且自己已执行完成已释放锁
 * -- t1被唤醒并能获取到锁继续执行
 * 主要t2要先于t1执行 join保证顺序执行
 */

import java.util.LinkedList;
import java.util.List;

public class T04_Sync_NotifyFreeLock {
  volatile List<Object> list=new LinkedList<>();
  public void add(Object o) {
    list.add(o);
  }

  public int size() {
    return list.size();
  }

  static Object lock = new Object();
  public static void main(String[] args) {
    T04_Sync_NotifyFreeLock t = new T04_Sync_NotifyFreeLock();
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        synchronized (lock) {
          t.add(new Object());
          System.out.println("add" + i);
          if (t.size() == 5) {
            lock.notify();
            try {
              lock.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }
    });

    Thread t2 = new Thread(() -> {
      synchronized (lock) {
        System.out.println("t2 启动");
        try {
          lock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }finally {
          lock.notify();
        }
        System.out.println("t2 已结束");
      }
    });

    t2.start();
    t1.start();
    //保证t2先执行，t1后执行
    try {
      t2.join();
      t1.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
