package com.zlg.juc.c_14_Reference;

import java.lang.ref.SoftReference;

/**
 * 软引用，只有内存不够用的时候才会回收，内存够用即使遭遇gc也不会回收
 * 需要设置运行参数 -Xmx20M
 */
public class T02_SoftReference {
  public static void main(String[] args) throws InterruptedException {
    SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);

    //m = null;
    System.out.println(m.get());
    System.gc();
    Thread.sleep(500);
    System.out.println(m.get());

    byte[] b = new byte[1024 * 1024 * 15];
    System.out.println(m.get());
  }
}
