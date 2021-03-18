package com.zlg.juc.c_18_interview_1A2B3C;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

public class T06_TransferQueue {
  static char[] a = "1234567".toCharArray();
  static char[] b = "ABCDEFG".toCharArray();

  static LinkedTransferQueue q = new LinkedTransferQueue();
  public static void main(String[] args) {
    new Thread(() -> {
      for (char c : a) {
        try {
          q.transfer(c);
          System.out.println(c);
          q.take();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();

    new Thread(()-> {
      for (char c : b) {
        try {
          q.take();
          q.transfer(c);
          System.out.println(c);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();

  }
}
