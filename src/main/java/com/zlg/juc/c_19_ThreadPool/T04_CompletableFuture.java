package com.zlg.juc.c_19_ThreadPool;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class T04_CompletableFuture {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    CompletableFuture<Double> future1 = CompletableFuture.supplyAsync(()->{return priceOfJD();});
    CompletableFuture<Double> future2 = CompletableFuture.supplyAsync(()->{return priceOfTB();});
    CompletableFuture<Double> future3 = CompletableFuture.supplyAsync(()->{return priceOfTM();});

    CompletableFuture.allOf(future1, future2, future3).join();
//    CompletableFuture.anyOf(future1, future2, future3).join();
    try {
      System.out.println("jd price:"+ future1.get());
      System.out.println("tb price:"+ future2.get());
      System.out.println("tm price:"+ future3.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    long end = System.currentTimeMillis();
    System.out.println(end - start);
  }
  private static double priceOfTM() {
    delay();
    return 1.00;
  }

  private static double priceOfTB() {
    delay();
    return 2.00;
  }

  private static double priceOfJD() {
    delay();
    return 3.00;
  }

  private static void delay() {
    int time = new Random().nextInt(500);
    try {
      TimeUnit.MILLISECONDS.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.printf("After %s sleep!\n", time);
  }
}
