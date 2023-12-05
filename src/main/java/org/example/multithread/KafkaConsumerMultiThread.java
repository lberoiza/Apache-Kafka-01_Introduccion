package org.example.multithread;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.example.KafkaProducerExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaConsumerMultiThread extends Thread {

  private final KafkaConsumer<String, String> consumer;
  private final AtomicBoolean closed = new AtomicBoolean(false);
  public static final Logger log = LoggerFactory.getLogger(KafkaConsumerMultiThread.class);

  public KafkaConsumerMultiThread(KafkaConsumer<String, String> consumer) {
    this.consumer = consumer;
  }

  @Override
  public void run() {
    try {
      consumer.subscribe(List.of(KafkaProducerExample.TOPIC_NAME));
      while (!closed.get()) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
        for (ConsumerRecord<String, String> record : records) {
          log.info("offset = {}, partition = {} key = {}, value = {} ", record.offset(), record.partition(), record.key(), record.value());
        }
      }
    } catch (WakeupException e) {
      if (!closed.get())
        throw e;
    } finally {
      consumer.close();
    }
  }

  public void shutdown() {
    closed.set(true);
    consumer.wakeup();
  }

}
