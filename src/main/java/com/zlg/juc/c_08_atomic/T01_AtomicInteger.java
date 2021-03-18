package com.zlg.juc.c_08_atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS 更高效的原子性操作
 * 计数器程序通过AtomicInteger完成，保证原子性
 */
public class T01_AtomicInteger {
  AtomicInteger count = new AtomicInteger();

  public void m() {
    for(int i=0; i < 10000; i++) {
//      count.compareAndSet(i, i+1);
      count.incrementAndGet();//count++
    }
  }

  public static void main(String[] args) {
    List<Thread> list = new ArrayList<>();
    T01_AtomicInteger t = new T01_AtomicInteger();
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

    System.out.println(t.count.intValue());
  }
}
