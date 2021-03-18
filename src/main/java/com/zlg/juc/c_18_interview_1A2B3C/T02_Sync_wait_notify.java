package com.zlg.juc.c_18_interview_1A2B3C;

public class T02_Sync_wait_notify {
  public static void main(String[] args) {
    char[] a = "1234567".toCharArray();
    char[] b = "ABCDEFG".toCharArray();

    Object lock = new Object();
    Thread t1 = new Thread(() -> {
      for (char c : a) {
        synchronized (lock) {
          try {
            lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.print(c);
          lock.notify();
        }
      }
    });

    Thread t2 = new Thread(() -> {
      for (char c : b) {
        synchronized (lock) {
          lock.notify();
          try {
            lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.print(c);
        }
      }
    });

    t1.start();
    t2.start();
  }
}
