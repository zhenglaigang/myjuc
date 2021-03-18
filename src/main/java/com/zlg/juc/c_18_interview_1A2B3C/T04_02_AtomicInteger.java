package com.zlg.juc.c_18_interview_1A2B3C;

import java.util.concurrent.atomic.AtomicInteger;

public class T04_02_AtomicInteger {
  static char[] a = "1234567".toCharArray();
  static char[] b = "ABCDEFG".toCharArray();

  //替代使用volatile
  static AtomicInteger ai = new AtomicInteger(1);
  public static void main(String[] args) {
    new Thread(() -> {
      for (char c : a) {
        while (ai.intValue() != 1) {}//只是为了阻塞
        System.out.print(c);
        ai.set(2);
      }
    }).start();

    new Thread(() -> {
      for (char c : b) {
        while (ai.intValue() != 2) {}
        System.out.print(c);
        ai.set(1);
      }
    }).start();


  }
}
