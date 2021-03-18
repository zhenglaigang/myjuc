package com.zlg.juc.c_19_ThreadPool;

import java.util.concurrent.*;

public class T02_Callable {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    Callable c = new Callable<>() {
      @Override
      public Object call() throws Exception {
        return "hello callable";
      }
    };

    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    Future<String> f = singleThreadExecutor.submit(c);//异步

    String s = f.get();//阻塞
    System.out.println(s);
    singleThreadExecutor.shutdown();
  }
}
