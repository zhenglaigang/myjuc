package com.zlg.juc.c_18_interview_1A2B3C;

public class T04_01_CAS {
  enum ReadyToRun {T1, T2}
  static char[] a = "1234567".toCharArray();
  static char[] b = "ABCDEFG".toCharArray();

  static volatile ReadyToRun running = ReadyToRun.T1;//必须加volatile
  public static void main(String[] args) {
    new Thread(() -> {
      for (char c : a) {
        while (!running.equals(ReadyToRun.T1)) {}//只是为了阻塞
        System.out.print(c);
        running = ReadyToRun.T2;
      }
    }).start();

    new Thread(() -> {
      for (char c : b) {
        while (!running.equals(ReadyToRun.T2)) {}
        System.out.print(c);
        running = ReadyToRun.T1;
      }
    }).start();


  }
}
