package com.zlg.disruptor.v5_ProducerType;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.zlg.disruptor.LongEvent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T01_ProducerType {
  public static void main(String[] args) {
    final int ringBufferSize = 1024;
    Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new,
        ringBufferSize, Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());

    disruptor.handleEventsWith((event, sequence, isEnd) -> {
      System.out.println(Thread.currentThread().getName() + " sequence：" + sequence + " value:" + event.getValue());
    });
    disruptor.start();

    int threadNum = 50;
    CyclicBarrier barrier = new CyclicBarrier(threadNum);
    ExecutorService service = Executors.newCachedThreadPool();
    RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
    for (int i = 0; i < threadNum; i++) {
      final long threadCount = i;
      service.submit(() -> {
        System.out.printf("thread %s ready to product\n", threadCount);
        try {
          barrier.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (BrokenBarrierException e) {
          e.printStackTrace();
        }

        for (int j = 0; j < 100; j++) {
          ringBuffer.publishEvent((event, sequence) -> {
            event.setValue(threadCount);
            System.out.println("生产了" + threadCount);
          });
        }
      });
    }

    service.shutdown();
    //disruptor.shutdown();
  }
}
