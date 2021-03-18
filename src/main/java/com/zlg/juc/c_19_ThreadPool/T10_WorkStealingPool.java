package com.zlg.juc.c_19_ThreadPool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WorkStealingPool 创建一个cpu核数的ForkJoinPool
 */
public class T10_WorkStealingPool {
  public static void main(String[] args) throws IOException {
    ExecutorService service = Executors.newWorkStealingPool();
    System.out.println("线程数：" + Runtime.getRuntime().availableProcessors());

    service.submit(new R(1000));
    service.submit(new R(2000));
    service.submit(new R(2000));
    service.submit(new R(2000));
    service.submit(new R(2000));
    service.submit(new R(2000));
    service.submit(new R(2000));
    service.submit(new R(2000));
    service.submit(new R(2000));

    System.in.read();
  }

  static class R implements Runnable {

    long time;

    public R(long time) {
      this.time = time;
    }

    @Override
    public void run() {
      try {
        Thread.sleep(time);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(time + " " + Thread.currentThread().getName());
    }
  }
}
