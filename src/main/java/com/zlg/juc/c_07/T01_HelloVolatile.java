package com.zlg.juc.c_07;

/**
 * volatile 保证线程之间数据可见性
 * 变量running如果不用volatile修饰，主线程对running的修改，其他线程不可见，while将一直运行
 */
public class T01_HelloVolatile {
  private static volatile boolean running = true;
  public static void main(String[] args) {
    new Thread(() -> {
      System.out.println("start");
      while (running) {}
      System.out.println("end");
    }).start();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    running = false;
  }
}
