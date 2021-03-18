package com.zlg.juc.c_11_interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer02<T> {
  private LinkedList<T> list = new LinkedList<>();
  private int count = 0;
  private int MAX = 10;

  static ReentrantLock lock = new ReentrantLock();
  Condition producer = lock.newCondition();
  Condition consumer = lock.newCondition();
  public void put(T t) {
    try {
      lock.lock();
      while (list.size() == MAX) {
        producer.await();
      }
      list.add(t);
      ++count;
      consumer.signalAll();//通知所有消费线程
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public T get() {
    T t = null;
    try {
      lock.lock();
      while (list.size() == 0) {
          consumer.await();
      }
      t = list.removeFirst();
      count--;
      producer.signalAll();//通知所有生产线程
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
    return t;
  }

  public static void main(String[] args) {
    MyContainer02<Object> c = new MyContainer02<>();

    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        for (int j = 0; j < 5; j++) {
          System.out.println(c.get());
        }
      }, "c" + i).start();
    }

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < 2; i++) {
      new Thread(() -> {
        for (int j = 0; j < 25; j++) {
          c.put(Thread.currentThread().getName() + "_" + j);
        }
      }, "p" + i).start();
    }
  }
}
