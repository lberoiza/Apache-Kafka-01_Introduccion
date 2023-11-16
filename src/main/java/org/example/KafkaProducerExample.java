package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class KafkaProducerExample {

  public static void main(String[] args) {
    System.out.println("Start");
    KafkaProducer<String, String> producer = KafkaProducerFactory.getKafkaProducer();
    String topic = "test-topic-2";
    int partition = 0;
    String key = "testKey";
    String value = "testValue";
    producer.send(new ProducerRecord<>(topic,partition,key, value));
    producer.close();
    System.out.println("End");
  }


}
