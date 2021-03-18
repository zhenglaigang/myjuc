package com.zlg.juc.c_08_atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 证明AtomicXXX 线程可见性
 */
public class T03_AtomicVisibility implements Runnable {
  AtomicBoolean ab = new AtomicBoolean(true);
  @Override
  public void run() {
    System.out.println("start");
    while (ab.get()) {}
    System.out.println("end");
  }

  public static void main(String[] args) {
    T03_AtomicVisibility t = new T03_AtomicVisibility();
    new Thread(t).start();

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    t.ab.set(false);
  }
}
