package com.zlg.juc.c_14_Reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用，遇到gc就会被回收
 */
public class T03_WeakReference {
  public static void main(String[] args) {
    WeakReference m = new WeakReference(new T01_NormalReference.M());

    System.out.println(m.get());
    System.gc();
    System.out.println(m.get());

    ThreadLocal<T01_NormalReference.M> tl = new ThreadLocal<>();
    tl.set(new T01_NormalReference.M());
    tl.remove();//ThreadLocal用完要手动remove避免内存泄漏
  }
}
