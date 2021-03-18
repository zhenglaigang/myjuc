package com.zlg.juc.c_01;

/**
 * 线程就是进程中的不同执行路径
 */
public class T01_WhatISThread {
  public static void main(String[] args) {
    new Thread(() -> {
      for (int i=0; i<10; i++) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("T1");
      }
    }).start();
    for (int i=0; i<10; i++) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("main");
    }
  }
}
