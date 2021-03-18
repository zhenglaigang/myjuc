package com.zlg.juc.c_09;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 用来代替Synchronized 进行同步
 *
 * 不过ReentrantLock，不会和Synchronized一样自动释放锁，所以需要手动释放锁 手动释放锁 手动释放锁
 * 发生异常也不会释放锁，所以需要finally 释放锁
 *
 * ReentrantLock支持尝试锁定tryLock，如果线程无法锁定或在指定时间内无法锁定，线程可以决定结束等待或进行其他处理
 */
public class T03_ReentrantLock_03 {
  private int count = 100;

  ReentrantLock lock = new ReentrantLock();

  public void m() {
    try {
      lock.lock();
      for (int i=0; i<3; i++) {
        Thread.sleep(1000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }finally{
      lock.unlock();//手动释放锁 手动释放锁 手动释放锁
    }
  }

  public void n() {
    boolean locked = false;
    try {
//      locked = lock.tryLock();
      locked = lock.tryLock(5, TimeUnit.SECONDS);//5s内获得锁
      System.out.println("locked:"+locked);
    } catch (Exception e) {
      e.printStackTrace();
    }finally{
      if (locked) lock.unlock();//手动释放锁 手动释放锁 手动释放锁
    }
  }

  public static void main(String[] args) {

    T03_ReentrantLock_03 t = new T03_ReentrantLock_03();
    new Thread(t::m).start();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    new Thread(t::n).start();
  }
}
