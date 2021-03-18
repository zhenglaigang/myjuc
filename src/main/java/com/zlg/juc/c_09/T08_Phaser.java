package com.zlg.juc.c_09;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class T08_Phaser {
  static MarriagePhaser phaser = new MarriagePhaser();

  public static void main(String[] args) {
    phaser.bulkRegister(7);
    for (int i = 0; i < 5; i++) {
      new Thread(new Person("p" + i)).start();
    }

    new Thread(new Person("新郎")).start();
    new Thread(new Person("新娘")).start();
  }

  static class MarriagePhaser extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
      switch (phase) {
        case 0:
          System.out.println("所有到达了");
          return false;
        case 1:
          System.out.println("所有人吃完了");
          return false;
        case 2:
          System.out.println("所有人离开了");
          return false;
        case 3:
          System.out.println("可以抱抱了");
          return true;
        default:
          return true;
      }
    }
  }

  static class Person implements Runnable {
    public Person(String name) {
      this.name = name;
    }

    String name;
    @Override
    public void run() {
      arrive();
      eat();
      leave();
      hug();
    }

    public void arrive() {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(name + "到达");
      phaser.arriveAndAwaitAdvance();
    }

    public void eat() {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(name + "吃饭");
      phaser.arriveAndAwaitAdvance();
    }

    public void leave() {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(name + "离开");
      phaser.arriveAndAwaitAdvance();
    }

    public void hug() {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if ("新郎".equals(name) || "新娘".equals(name)) {
        System.out.println(name + "抱抱");
        phaser.arriveAndAwaitAdvance();
      }else {
        System.out.println(name + "结束");
        phaser.arriveAndDeregister();
      }
    }
  }
}
