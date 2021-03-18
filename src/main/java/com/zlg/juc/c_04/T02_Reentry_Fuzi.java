package com.zlg.juc.c_04;

/**
 * synchronized 同步可重入性验证
 * java的继承体系要求锁必须是可重入的
 */
public class T02_Reentry_Fuzi {
  static class Father {
    protected synchronized void m() {
      System.out.println("m");
    }
  }

  static class Child extends Father {
    @Override
    protected synchronized void m() {
      System.out.println("n start");
      super.m();
      System.out.println("n end");
    }
  }

  public static void main(String[] args) {
    Child child = new Child();
    child.m();
  }
}
