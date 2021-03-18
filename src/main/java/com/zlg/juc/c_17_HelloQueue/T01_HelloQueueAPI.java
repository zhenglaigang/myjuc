package com.zlg.juc.c_17_HelloQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T01_HelloQueueAPI {
  public static void main(String[] args) {
    Queue<String> q = new ConcurrentLinkedQueue();
    for (int i = 0; i < 10; i++) {
      boolean offer = q.offer("s" + i);
      System.out.println(offer);
    }

    System.out.println(q);
    String peek = q.peek();

    System.out.println(q.size());
    String poll = q.poll();

    System.out.println(q.size());
  }
}
