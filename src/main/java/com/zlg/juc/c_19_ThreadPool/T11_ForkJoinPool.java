package com.zlg.juc.c_19_ThreadPool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 模拟 从 start 到 end 数相加 拆分任务执行
 */
public class T11_ForkJoinPool {
  static int[] nums = new int[1000000];
  static final int MAX_NUM = 50000;

  static Random r = new Random();
  static {
    for (int i = 0; i < nums.length; i++) {
      nums[i] = r.nextInt(100);
    }
    System.out.println(Arrays.stream(nums).sum());
  }

  static class AddTaskRet extends RecursiveTask<Long> {

    int start, end;

    public AddTaskRet(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    protected Long compute() {
      if (end - start < MAX_NUM) {
        long sum = 0;
        for (int i = start; i < end; i++) sum += nums[i];
        return sum;
      }
      int middle = start + (end - start) / 2;
      AddTaskRet taskRet1 = new AddTaskRet(start, middle);
      AddTaskRet taskRet2 = new AddTaskRet(middle, end);

      taskRet1.fork();//fork
      taskRet2.fork();

      return taskRet1.join() + taskRet2.join();//join
    }
  }

  public static void main(String[] args) throws Exception {
    ForkJoinPool fjp = new ForkJoinPool();
    AddTaskRet taskRet = new AddTaskRet(0, nums.length);
    fjp.execute(taskRet);
    Long aLong = taskRet.join();
    System.out.println(aLong);
  }
}
