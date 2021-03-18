package com.zlg.juc.c_14_Reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;

/**
 * 虚引用
 */
public class T04_PhantomReference {
  static LinkedList list = new LinkedList();
  final static ReferenceQueue<T01_NormalReference.M> queue = new ReferenceQueue<>();
  public static void main(String[] args) {
    PhantomReference phantomReference = new PhantomReference(new T01_NormalReference.M(),queue);
    new Thread(() -> {
      while (true) {
        list.add(new byte[1024*1024]);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(phantomReference.get());
      }
    }).start();

    new Thread(() -> {
      while (true) {
        Reference<? extends T01_NormalReference.M> poll = queue.poll();
        if (poll != null) {
          System.out.println("一个虚引用的对象被回收了" + poll);
        }
      }
    }).start();
  }
}
