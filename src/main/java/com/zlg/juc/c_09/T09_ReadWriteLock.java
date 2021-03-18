package com.zlg.juc.c_09;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T09_ReadWriteLock {
//  static ReentrantLock lock = new ReentrantLock();
  static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  static Lock readLock = readWriteLock.readLock();
  static Lock writeLock = readWriteLock.writeLock();

  public static void main(String[] args) {
//    for (int i = 0; i < 18; i++) new Thread(() -> {read(lock);}).start();
//    for (int i = 0; i < 2; i++) new Thread(() -> {write(lock, new Random().nextInt(100));}).start();

    //读用读锁，写用写锁
    for (int i = 0; i < 18; i++) new Thread(() -> {read(readLock);}).start();
    for (int i = 0; i < 2; i++) new Thread(() -> {write(writeLock, new Random().nextInt(100));}).start();
  }

  //模拟读操作
  public static void read(Lock lock) {
    try {
      lock.lock();
      TimeUnit.SECONDS.sleep(1);
      System.out.println("读操作");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  //模拟写操作
  public static void write(Lock lock, int v) {
    try {
      lock.lock();
      TimeUnit.SECONDS.sleep(1);
      System.out.println("写操作");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

}
