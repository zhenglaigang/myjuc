package com.zlg.juc.c_17_HelloQueue;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class T03_PriorityQueue {
  static Queue<String> q = new PriorityQueue();

  public static void main(String[] args) {
    q.add("c");
    q.offer("a");
    q.offer("z");
    q.offer("x");

    for (int i = 0; i < 4; i++) {
      System.out.println(q.poll());
    }
  }
}
