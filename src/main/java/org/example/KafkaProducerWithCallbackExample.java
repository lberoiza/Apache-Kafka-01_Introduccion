package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class KafkaProducerWithCallbackExample {

  public static void main(String[] args) {
    KafkaProducer<String, String> producer = KafkaProducerFactory.getKafkaProducer();
    String topic = "test-topic-2";
    String key = "testKey";
    String value = "testValue";
    final ProducerRecord<String, String> record = new
        ProducerRecord<>(topic, key, value);
    producer.send(record, (recordMetadata, e) -> {
      if (e != null) {
        System.out.println("Send failed for record: " + e.getMessage());
      }
    });

    producer.close();
  }



}
