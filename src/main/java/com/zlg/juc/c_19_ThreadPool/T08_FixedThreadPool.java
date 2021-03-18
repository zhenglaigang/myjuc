package com.zlg.juc.c_19_ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class T08_FixedThreadPool {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    long start = System.currentTimeMillis();
    getPrime(1, 200000);
    long end = System.currentTimeMillis();
    System.out.println(end - start);

    start = System.currentTimeMillis();
    final int threadNum = 5;
    ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
    Future<List<Integer>> f1 = executorService.submit(new MyTask(1, 40000));
    Future<List<Integer>> f2 =executorService.submit(new MyTask(40001, 100000));
    Future<List<Integer>> f3 =executorService.submit(new MyTask(100001, 150000));
    Future<List<Integer>> f4 =executorService.submit(new MyTask(150001, 180000));
    Future<List<Integer>> f5 =executorService.submit(new MyTask(180001, 200000));

    f1.get();
    f2.get();
    f3.get();
    f4.get();
    f5.get();
    end = System.currentTimeMillis();

    System.out.println(end - start);
    executorService.shutdown();
  }

  static class MyTask implements Callable {
    int startPos, endPos;
    public MyTask(int startPos, int endPos) {
      this.startPos = startPos;
      this.endPos = endPos;
    }
    @Override
    public List<Integer> call() throws Exception {
      List<Integer> list = getPrime(startPos, endPos);
      return list;
    }
  }

  static boolean isPrime(int num) {
    for(int i=2; i<=num/2; i++) {
      if(num % i == 0) return false;
    }
    return true;
  }

  static List<Integer> getPrime(int start, int end) {
    List<Integer> results = new ArrayList<>();
    for(int i=start; i<=end; i++) {
      if(isPrime(i)) results.add(i);
    }

    return results;
  }
}
