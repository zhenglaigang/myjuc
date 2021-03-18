package com.zlg.juc.c_09;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore信号量
 *
 * 有信号了就可以执行
 */
public class T10_Semaphore {
//  static Semaphore lock = new Semaphore(1);
//  static Semaphore lock = new Semaphore(2);
  static Semaphore lock = new Semaphore(2, true);//支持公平锁

  public static void main(String[] args) {
    new Thread(() -> {
      try {
        lock.acquire();//获取锁，阻塞方法，等待有信号继续执行
        System.out.println("t1 running");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("t1 running");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.release();//释放信号锁
      }
    }).start();
    new Thread(() -> {
      try {
        lock.acquire();
        System.out.println("t2 running");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("t2 running");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.release();
      }
    }).start();
  }

}
