package com.zlg.juc.c_14_Reference;

import java.io.IOException;

public class T01_NormalReference {
  public static void main(String[] args) throws IOException {
    M m = new M();
    m = null;
    System.gc();

    System.in.read();
  }

  static class M {
    @Override
    protected void finalize() throws Throwable {//只是为了被回收，看到消息
      System.out.println("finalize");;
    }
  }
}
