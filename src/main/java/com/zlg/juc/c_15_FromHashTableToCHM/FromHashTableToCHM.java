package com.zlg.juc.c_15_FromHashTableToCHM;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FromHashTableToCHM {
  static Hashtable<UUID, UUID> m = new Hashtable();
//  static Map<UUID, UUID> m = new HashMap<>();
//  static Map<UUID, UUID> m = Collections.synchronizedMap(new HashMap<>());
//  static ConcurrentHashMap m = new ConcurrentHashMap();

  static UUID[] keys = new UUID[100_0000];
  static UUID[] values = new UUID[100_0000];
  final static int count = 100_0000;
  static int THREAD_COUNT = 100;
  static {
    for (int i = 0; i < count; i++) {
      keys[i] = UUID.randomUUID();
      values[i] = UUID.randomUUID();
    }
  }

  static class MyThread implements Runnable {
    int start;
    int gap = count/THREAD_COUNT;

    public MyThread(int start) {
      this.start = start;
    }
    @Override
    public void run() {
      for (int i = start*gap; i < (start+1)*gap; i++) {
        m.put(keys[i], values[i]);
      }
    }
  }

  public static void main(String[] args) {
    //--测试写-------------------------------------------
    long startTime = System.currentTimeMillis();
    Thread[] threads = new Thread[THREAD_COUNT];
    for (int i = 0; i < THREAD_COUNT; i++) {
      threads[i] = new Thread(new MyThread(i));
    }

    for (int i = 0; i < THREAD_COUNT; i++) {
      threads[i].start();
    }

    for (int i = 0; i < THREAD_COUNT; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    long endTime = System.currentTimeMillis();
    System.out.println(endTime - startTime);
    System.out.println(m.size());

    //--测试读----------------------------------------
    startTime = System.currentTimeMillis();
    for (int i = 0; i < THREAD_COUNT; i++) {
      threads[i] = new Thread(() -> {
        for (int j = 0; j < count; j++) {
          m.get(keys[10]);
        }
      });
    }

    for (int i = 0; i < THREAD_COUNT; i++) {
      threads[i].start();
    }

    for (int i = 0; i < THREAD_COUNT; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    endTime = System.currentTimeMillis();
    System.out.println(endTime - startTime);
    System.out.println(m.size());
  }
}
