package com.zlg.juc.c_19_ThreadPool;

import java.util.concurrent.Executor;

public class T01_Executor implements Executor{
  @Override
  public void execute(Runnable command) {
//    new Thread(command).start();
    command.run();
  }

  public static void main(String[] args) {
    T01_Executor myExecutor = new T01_Executor();
    myExecutor.execute(new Runnable() {
      @Override
      public void run() {
        System.out.println("hello executor");
      }
    });
  }
}
