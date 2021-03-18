package com.zlg.juc.c_09;

import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 用来代替Synchronized 进行同步
 *
 * 不过ReentrantLock，不会和Synchronized一样自动释放锁，所以需要手动释放锁 手动释放锁 手动释放锁
 * 发生异常也不会释放锁，所以需要finally 释放锁
 */
public class T02_ReentrantLock_02 {
  private int count = 100;

  ReentrantLock lock = new ReentrantLock();
  public void m() {
    try {
      lock.lock();
      count--;
      System.out.println(Thread.currentThread().getName() + " count=" + count);
    } catch (Exception e) {
      e.printStackTrace();
    }finally{
      lock.unlock();//手动释放锁 手动释放锁 手动释放锁
    }
  }

  public static void main(String[] args) {
    T02_ReentrantLock_02 t = new T02_ReentrantLock_02();
    for (int i=0; i<100; i++) {
      new Thread(t::m).start();
    }
  }
}
