package com.zlg.juc.c_09;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 用来代替Synchronized 进行同步
 *
 * 不过ReentrantLock，不会和Synchronized一样自动释放锁，所以需要手动释放锁 手动释放锁 手动释放锁
 * 发生异常也不会释放锁，所以需要finally 释放锁
 *
 * ReentrantLock支持尝试锁定：tryLock，如果线程无法锁定或在指定时间内无法锁定，线程可以决定结束等待或进行其他处理
 *
 * ReentrantLock支持响应线程打断，可以对线程的interrupt方法进行响应
 * 一个线程在等待锁的过程中，可以被打断
 */
public class T04_ReentrantLock_04 {
  public static void main(String[] args) {
    ReentrantLock lock = new ReentrantLock();
    Thread t1 = new Thread(()->{
      try {
        lock.lock();
        System.out.println("t1 start");
        Thread.sleep(Integer.MAX_VALUE);
        System.out.println("t1 end");
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    });
    t1.start();

    Thread t2 = new Thread(() -> {
      try {
        lock.lockInterruptibly();
        System.out.println("t2 start");
        Thread.sleep(5000);
        System.out.println("t2 end");
      } catch (InterruptedException e) {
        System.out.println("interrupted");
      } finally {
        if (!lock.isLocked()) lock.unlock();
      }
    });
    t2.start();

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    t2.interrupt();
  }

}
