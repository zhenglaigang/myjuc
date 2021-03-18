package com.zlg.juc.c_07;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * volatile可见但不同步
 * volatile只能保证线程可见性，但不能保证多个线程执行过程中的线程同步问题
 * 程序模拟多个线程一块执行计数器，期望值是10000，但结果不是，说明线程不同步
 */
public class T02_VolatileNotSync {
  private volatile int count = 0;

  public /*synchronized*/ void m() {
    for (int i=0; i < 10000; i++ )
      count++;
  }

  public static void main(String[] args) {
    List<Thread> list = new ArrayList<>();
    T02_VolatileNotSync t = new T02_VolatileNotSync();
    for (int i=0; i<10; i++) {
      Thread thread = new Thread(t::m, "t" + i);
      list.add(thread);
    }

    list.forEach((thread) -> {
      thread.start();
    });

    //主线程等待所有线程执行完再输出
    list.forEach((thread) ->{
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    System.out.println(t.count);
  }
}
