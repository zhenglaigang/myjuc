package com.zlg.juc.c_19_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T07_CachedThreadPool {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();

  }
}
