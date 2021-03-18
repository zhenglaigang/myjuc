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
 *
 * ReentrantLock支持公平锁
 */
public class T05_ReentrantLock_05 implements Runnable {
  ReentrantLock lock = new ReentrantLock(false);

  @Override
  public void run() {
    for (int i=0; i<100; i++) {
      try {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "获取锁");
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }
  }

  public static void main(String[] args) {
    T05_ReentrantLock_05 t = new T05_ReentrantLock_05();
    new Thread(t, "t1").start();
    new Thread(t, "t2").start();
  }
}
