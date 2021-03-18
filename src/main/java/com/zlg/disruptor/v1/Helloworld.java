package com.zlg.disruptor.v1;

import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.zlg.disruptor.LongEvent;
import com.zlg.disruptor.LongEventFactory;
import com.zlg.disruptor.LongEventHandler;

import java.util.concurrent.Executors;

/**
 * disruptor helloworld
 */
public class Helloworld {
  public static void main(String[] args) {
    LongEventFactory eventFactory = new LongEventFactory();
    //must be power of 2
    final int ringBufferSize = 1024;
    // Construct the Disruptor
    Disruptor disruptor = new Disruptor(eventFactory, ringBufferSize, Executors.defaultThreadFactory());
    // Connect the handler
    LongEventHandler h1 = new LongEventHandler();
    LongEventHandler h2 = new LongEventHandler();
    disruptor.handleEventsWith(h1, h2);
    // Start the Disruptor, starts all threads running
    disruptor.start();
    RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
    long sequence = ringBuffer.next();// Grab the next sequence
    try {
      LongEvent event = ringBuffer.get(sequence);
      event.setValue(8888L);
    }finally {
      ringBuffer.publish(sequence);
    }
  }
}
