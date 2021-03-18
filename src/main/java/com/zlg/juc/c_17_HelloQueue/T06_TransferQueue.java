package com.zlg.juc.c_17_HelloQueue;

import java.util.concurrent.LinkedTransferQueue;

public class T06_TransferQueue {
  public static void main(String[] args) throws InterruptedException {
    LinkedTransferQueue<String> q = new LinkedTransferQueue<>();
    new  Thread(() -> {
      try {
        System.out.println(q.take());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    q.transfer("bbb");//transfer()方法装入队列，并阻塞住
  }
}
