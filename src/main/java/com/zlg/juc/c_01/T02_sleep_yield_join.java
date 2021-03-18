package com.zlg.juc.c_01;

public class T02_sleep_yield_join {
  public static void main(String[] args) throws InterruptedException {
//    testSleep();
//    testYeild();
    testJoin();
  }

  private static void testSleep() {
    new Thread(() -> {
      for (int i=0; i<10; i++) {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("A" + i);
      }
    }).start();
  }

  private static void testYeild() {
    new Thread(() -> {
      for (int i=0; i<100; i++) {
        if (i%10==0) {
          Thread.yield();
        }
        System.out.println("A" + i);
      }
    }).start();

    new Thread(() -> {
      for (int i=0; i<100; i++) {
        if (i%10==0) {
          Thread.yield();
        }
        System.out.println("B" + i);
      }
    }).start();
  }

  private static void testJoin() {
    System.out.println("start");
    Thread t1 = new Thread(() -> {
      for (int i=0; i<10; i++) {
        try {
          Thread.sleep(500);
          //TimeUnit.Milliseconds.sleep(500)
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("A" + i);
      }
    });

    Thread t2 = new Thread(() -> {
      try {
        t1.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      for (int i=0; i<10; i++) {
        try {
          Thread.sleep(500);
          //TimeUnit.Milliseconds.sleep(500)
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("B" + i);
      }
    });

    t1.start();
    t2.start();

    System.out.println("end");
  }
}
