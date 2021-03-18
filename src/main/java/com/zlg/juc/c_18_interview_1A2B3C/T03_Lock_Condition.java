package com.zlg.juc.c_18_interview_1A2B3C;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T03_Lock_Condition {
  static Lock lock = new ReentrantLock();
  static Condition c1 = lock.newCondition();
  static Condition c2 = lock.newCondition();

  public static void main(String[] args) {
    char[] a = "1234567".toCharArray();
    char[] b = "ABCDEFG".toCharArray();
    Thread t1 = new Thread(() -> {
      for (char c : a) {
        lock.lock();
        try {
          c1.await();
          System.out.print(c);
          c2.signal();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }finally {
          lock.unlock();
        }

      }
    });
    Thread t2 = new Thread(() -> {
      for (char c : b) {
        lock.lock();
        try {
          c1.signal();
          c2.await();
          System.out.print(c);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }finally {
          lock.unlock();
        }
      }
    });
    t1.start();
    t2.start();
  }
}
