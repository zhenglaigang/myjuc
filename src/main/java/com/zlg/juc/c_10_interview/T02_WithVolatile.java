package com.zlg.juc.c_10_interview;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * ArrayList + OnlyTwoThread
 * 问题1：线程之间数据不可见，无法实现
 * 问题2：存在同步问题，add() 完成后会size++，不能保证原子性
 *
 *  volatile + 同步容器
 *  多次测试，结果不一
 *  大部分t2得到了通知，偶现t2得不到通知的情况，而且这里volatile修饰的是引用类型，理论上就是应该不通知
 *  同步容器没有达到同步的效果
 *  所以关于volatile
 *  没有把握的情况不要用
 *  用的话，也只用于基本数据类型，不要用于引用数据类型
 *  另while(true)死循环 很消耗cpu资源
 */
public class T02_WithVolatile {

//  volatile List<Object> list = new LinkedList<>();

  volatile List<Object> list = Collections.synchronizedList(new LinkedList<>());

  public void add(Object o) {
    list.add(o);
  }

  public int size() {
    return list.size();
  }

  public static void main(String[] args) {
    T02_WithVolatile t = new T02_WithVolatile();
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        t.add(new Object());
        System.out.println("add"+i);
      }
    });

    Thread t2 = new Thread(() -> {
      while (true) {
        if (t.size() == 5) {
          break;
        }
      }
      System.out.println("t2 已结束");
    });

    t1.start();
    t2.start();
  }
}
