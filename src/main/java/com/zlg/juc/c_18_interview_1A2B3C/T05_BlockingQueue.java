package com.zlg.juc.c_18_interview_1A2B3C;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class T05_BlockingQueue {
  static char[] a = "1234567".toCharArray();
  static char[] b = "ABCDEFG".toCharArray();

  static BlockingQueue<Character> q1 = new ArrayBlockingQueue(1);
  static BlockingQueue<Character> q2 = new ArrayBlockingQueue(1);
  public static void main(String[] args) {
    new Thread(() -> {
      for (char c : a) {
        try {
          q1.put(c);
          System.out.println(c);
          q2.take();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
    new Thread(() -> {
      for (char c : b) {
        try {
          q1.take();
          System.out.println(c);
          q2.put(c);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

}
