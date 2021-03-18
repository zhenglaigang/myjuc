package com.zlg.juc.c_17_HelloQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class T04_DelayQueue {
  static BlockingQueue<Delayed> q = new DelayQueue<>();
  static class MyTask implements Delayed {
    String name;
    long runningTime;

    MyTask(String name, long rt) {
      this.name = name;
      this.runningTime = rt;
    }

    @Override
    public int compareTo(Delayed o) {
      if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
        return -1;
      else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
        return 1;
      else
        return 0;
    }

    @Override
    public long getDelay(TimeUnit unit) {

      return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }


    @Override
    public String toString() {
      return name + " " + runningTime;
    }
  }
  public static void main(String[] args) throws InterruptedException {
    long now = System.currentTimeMillis();
    q.put(new MyTask("t1", now + 2500));
    q.put(new MyTask("t2", now + 1000));
    q.put(new MyTask("t3", now + 1500));
    q.put(new MyTask("t4", now + 500));

    for (int i = 0; i < 4; i++) {
      System.out.println(q.take());
    }
  }
}
