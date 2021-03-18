package com.zlg.disruptor.v4;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.zlg.disruptor.LongEvent;
import com.zlg.disruptor.LongEventFactory;
import com.zlg.disruptor.LongEventHandler;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

/**
 * lamdaAPI
 * 不需要建类 EventFactory EventHandler EventProducer
 */
public class Main {
  public static void main(String[] args) {
    final int ringBufferSize = 1024;
    Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, ringBufferSize, DaemonThreadFactory.INSTANCE);

    disruptor.handleEventsWith((event, sequence, isEnd) -> {
      System.out.println(Thread.currentThread().getName() + " sequence：" + sequence + " value:" + event.getValue());
    });
    disruptor.start();
    RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
    ByteBuffer buffer = ByteBuffer.allocate(8);//分配内存，long占8字节
    for (long i = 0; true; i++) {
      buffer.putLong(0, i);
      //不推荐，这样写会创建很多对象来承接buffer，浪费效率
      //ringBuffer.publishEvent((event, sequence) -> event.setValue(buffer.getLong(0)));

      //buffer当参数传进去
//      ringBuffer.publishEvent((event, sequence, bb) -> event.setValue(bb.getLong(0)), buffer);

      //lamda表达式，方法提前定义好 (最好)
      ringBuffer.publishEvent(Main::translate, buffer);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void translate(LongEvent event, long sequence, ByteBuffer bb) {
    event.setValue(bb.getLong(0));
  }
}
