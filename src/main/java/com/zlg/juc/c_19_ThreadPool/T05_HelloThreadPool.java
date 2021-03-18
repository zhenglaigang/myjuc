package com.zlg.juc.c_19_ThreadPool;

import java.io.IOException;
import java.util.concurrent.*;

public class T05_HelloThreadPool {
  static class Task implements Runnable {
    private int i;
    public Task(int i) {
      this.i = i;
    }
    @Override
    public void run() {
      //给线程命名，出问题回溯
      System.out.println(Thread.currentThread().getName() + " task" + i);
      try {
        System.in.read();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    @Override
    public String toString() {
      return "Task{" +
          "i=" + i +
          '}';
    }
  }

  public static void main(String[] args) {
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
        2,
        4,
        60,
        TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(4),
        Executors.defaultThreadFactory(),
        new ThreadPoolExecutor.DiscardOldestPolicy()
      );

    for (int i = 0; i < 8; i++) {
      threadPoolExecutor.execute(new Task(i));
    }
    System.out.println(threadPoolExecutor.getQueue());

    threadPoolExecutor.execute(new Task(100));
    System.out.println(threadPoolExecutor.getQueue());

    threadPoolExecutor.shutdown();
  }
}
