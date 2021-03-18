package com.zlg.juc.c_19_ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T09_ScheduledThreadPool {
  public static void main(String[] args) {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
    executorService.scheduleAtFixedRate(()->{
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName());
    },0, 500, TimeUnit.MILLISECONDS);
  }
}
