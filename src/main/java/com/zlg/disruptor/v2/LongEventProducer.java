package com.zlg.disruptor.v2;

import com.lmax.disruptor.RingBuffer;
import com.zlg.disruptor.LongEvent;

import java.nio.ByteBuffer;

public class LongEventProducer {
  private RingBuffer<LongEvent> ringBuffer;

  public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
    this.ringBuffer = ringBuffer;
  }

  public void onData(ByteBuffer buffer) {
    long sequence = ringBuffer.next();// Grab the next sequence
    try {
      LongEvent event = ringBuffer.get(sequence);
      long val = buffer.getLong(0);
      event.setValue(val);
    }finally {
      ringBuffer.publish(sequence);
    }
  }
}
