package com.zlg.juc.c_17_HelloQueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class T02_TestBlockingQueue {
//  static BlockingQueue strs = new LinkedBlockingQueue(10);
  static BlockingQueue strs = new ArrayBlockingQueue(10);
  public static void main(String[] args) {
    Random r = new Random();
    //生产
    new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        try {
          strs.put("p" + i);                    //阻塞方法，满了就等待
          TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();

    //消费
    for (int i = 0; i < 5; i++) {
      int finalI = i;
      new Thread(() -> {
        for (;;) {
          try {                       //take 阻塞方法，如果空了就等待
            System.out.println(Thread.currentThread().getName() + " take " + strs.take());
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }, "c" + i).start();
    }
  }
}
