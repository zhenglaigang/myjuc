package com.zlg.juc.c_17_HelloQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class T05_SynchronusQueue {
  static BlockingQueue<String> q = new SynchronousQueue();//容量为0
  public static void main(String[] args) throws InterruptedException {
    new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(q.take());//消费后，put()阻塞结束继续执行
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    q.put("aaa");//阻塞等待消费
    System.out.println(q.size());//容量为0
  }
}
