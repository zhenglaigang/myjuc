package com.zlg.disruptor.v3;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.zlg.disruptor.LongEvent;

import java.nio.ByteBuffer;

/**
 * EventTranslator发布事件
 */
public class LongEventProducer {
  private RingBuffer ringBuffer;

  public LongEventProducer(RingBuffer ringBuffer) {
    this.ringBuffer = ringBuffer;
  }

  private final EventTranslatorOneArg eventTranslatorOneArg = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
    @Override
    public void translateTo(LongEvent event, long sequence, ByteBuffer bb) {
      event.setValue(bb.getLong(0));
    }
  };

  public void onData(ByteBuffer buffer) {
    ringBuffer.publishEvent(eventTranslatorOneArg, buffer);//不用自己处理异常；减少操作RingBuffer
  }
}
