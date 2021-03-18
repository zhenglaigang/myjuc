package com.zlg.juc.c_19_ThreadPool;

import java.util.ArrayList;
import java.util.Random;

public class T12_PanallStream {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList();
    Random r = new Random();
    for (int i = 0; i < 10000; i++) {
      list.add((1000000 + r.nextInt(1000000)));
    }

    long start = System.currentTimeMillis();
    list.stream().forEach(num ->isPrime(num));
    long end = System.currentTimeMillis();
    System.out.println(end - start);


    start = System.currentTimeMillis();
    list.parallelStream().forEach(T12_PanallStream::isPrime);
    end = System.currentTimeMillis();
    System.out.println(end - start);
  }
  private static boolean isPrime(long num) {
    for (int i = 2; i < num/2; i++) {
      if (num%i == 0) {
        return  false;
      }
    }
    return true;
  }
}
