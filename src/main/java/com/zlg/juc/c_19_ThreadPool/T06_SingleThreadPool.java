package com.zlg.juc.c_19_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T06_SingleThreadPool {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    for (int i = 0; i < 5; i++) {
      final int j = i;
      executorService.execute(() ->{
        System.out.println(j + " " + Thread.currentThread().getName());
      });
    }
    executorService.shutdown();
  }
}
