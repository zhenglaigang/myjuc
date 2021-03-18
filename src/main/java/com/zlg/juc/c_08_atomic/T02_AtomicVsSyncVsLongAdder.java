package com.zlg.juc.c_08_atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * AtomicXXX vs Sync
 * synchronized 锁对象使用this时，效率测试 比AtomicXXX高；使用指定的Object对象，比AtomicXXX低
 */
public class T02_AtomicVsSyncVsLongAdder {
  public long count = 0;
  public AtomicLong atomicLong = new AtomicLong(0L);
  public LongAdder longAdder = new LongAdder();

  Object lock = new Object();
  public void syncCount() {
    for (int i=0; i<100000; i++) {
//      synchronized (this) {count++;};//本例，测试比AtomicXXX效率高
      synchronized (lock) {count++;};
    }
  }

  public void atomicCount() {
    for (int i=0; i<100000; i++) {
      atomicLong.incrementAndGet();
    }
  }

  public void longAdderCount() {
    for (int i=0; i<100000; i++) {
      longAdder.increment();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    T02_AtomicVsSyncVsLongAdder t = new T02_AtomicVsSyncVsLongAdder();
    t.n();
    t.m();
    t.k();
  }

  public void m() throws InterruptedException {
    List<Thread> threads = new ArrayList<>();
    T02_AtomicVsSyncVsLongAdder t = new T02_AtomicVsSyncVsLongAdder();
    for (int i = 0; i < 1000; i++) {
      Thread thread = new Thread(t::syncCount, "t_sync");
      threads.add(thread);
    }

    long startTime = System.currentTimeMillis();
    for (Thread thread:threads) {
      thread.start();
    }
    for (Thread thread:threads) {thread.join();}

    long endTime = System.currentTimeMillis();
    System.out.println(t.count);
    System.out.println("sync_time:" + (endTime-startTime));
  }

  public void n() throws InterruptedException {
    List<Thread> threads = new ArrayList<>();
    T02_AtomicVsSyncVsLongAdder t = new T02_AtomicVsSyncVsLongAdder();
    for (int i = 0; i < 1000; i++) {
      Thread thread = new Thread(() -> {
        t.atomicCount();
      });
      threads.add(thread);
    }

    long startTime = System.currentTimeMillis();
    for (Thread thread:threads) {
      thread.start();
    }
    for (Thread thread:threads) {thread.join();}

    long endTime = System.currentTimeMillis();

    System.out.println("atomic_value=" + t.atomicLong.longValue());
    System.out.println("Atomic_time:" + (endTime-startTime));
  }

  public void k() throws InterruptedException {
    List<Thread> threads = new ArrayList<>();
    T02_AtomicVsSyncVsLongAdder t = new T02_AtomicVsSyncVsLongAdder();
    for (int i = 0; i < 1000; i++) {
      Thread thread = new Thread(() -> {
        t.longAdderCount();
      });
      threads.add(thread);
    }

    long startTime = System.currentTimeMillis();
    for (Thread thread:threads) {
      thread.start();
    }
    for (Thread thread:threads) {thread.join();}

    long endTime = System.currentTimeMillis();

    System.out.println("longAdder_value=" + t.longAdder.longValue());
    System.out.println("LongAdder_time:" + (endTime-startTime));
  }

}
