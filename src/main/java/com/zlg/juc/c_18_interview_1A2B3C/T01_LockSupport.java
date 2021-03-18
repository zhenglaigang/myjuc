package com.zlg.juc.c_18_interview_1A2B3C;

import java.util.concurrent.locks.LockSupport;

public class T01_LockSupport {
  static char[] a = "1234567".toCharArray();
  static char[] b = "ABCDEFG".toCharArray();

  static Thread t1 = null, t2 = null;
  public static void main(String[] args) {
    t1 = new Thread(() -> {
      for (char c : a) {
        LockSupport.park();
        System.out.print(c);
        LockSupport.unpark(t2);
      }
    }, "t1");

    t2 = new Thread(() -> {
      for (char c : b) {
        LockSupport.unpark(t1);
        LockSupport.park();
        System.out.print(c);
      }
    }, "t2");

    t1.start();
    t2.start();
  }
}
