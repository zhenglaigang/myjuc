package com.zlg.disruptor.v2;

import com.lmax.disruptor.dsl.Disruptor;
import com.zlg.disruptor.LongEventFactory;
import com.zlg.disruptor.LongEventHandler;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) {
    LongEventFactory eventFactory = new LongEventFactory();

    //must be power of 2
    final int ringBufferSize = 1024;

    // Construct the Disruptor
    Disruptor disruptor = new Disruptor(eventFactory, ringBufferSize, Executors.defaultThreadFactory());

    // Connect the handler
    disruptor.handleEventsWith(new LongEventHandler());

    // Start the Disruptor, starts all threads running
    disruptor.start();

    LongEventProducer producer = new LongEventProducer(disruptor.getRingBuffer());
    ByteBuffer buffer = ByteBuffer.allocate(8);//分配内存，long占8字节
    for (long i = 0; i < 100; i++) {
      buffer.putLong(0, i);
      producer.onData(buffer);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    disruptor.shutdown();
  }
}
