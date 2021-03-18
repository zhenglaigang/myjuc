package com.zlg.juc.c_12_helloVarHandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class Main {
  private int x = 100;
  private static VarHandle handle;
  static {
    try {
      handle = MethodHandles.lookup().findVarHandle(Main.class, "x", int.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Main t = new Main();
    handle.set(t, 1);
    System.out.println((int)handle.get(t));

    handle.compareAndSet(t, 1, 2);
    System.out.println(t.x);

    handle.getAndAdd(t, -1);
    System.out.println(t.x);
  }
}
