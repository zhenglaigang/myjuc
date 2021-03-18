package com.zlg.juc.c_09;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T12_LockSupport {
  public static void main(String[] args) {
    Thread t = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        System.out.println(i);
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if (i == 5)
          LockSupport.park();
      }

    });
    t.start();
    LockSupport.unpark(t);//可以先于part()执行unpark()

    new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(8);
        LockSupport.unpark(t);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }
}
