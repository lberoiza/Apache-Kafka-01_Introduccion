package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class KafkaProducerExample {

  public static final String TOPIC_NAME = "test-topic";

  public static void main(String[] args) {
    System.out.println("Starting Kafka Producer Example");
    KafkaProducer<String, String> producer = KafkaProducerFactory.getKafkaProducer();
    int partition = 0;
    String key = "testKey";
    String value = "testValue";

    System.out.println("generate Record");
    ProducerRecord<String, String> recordToSend = new ProducerRecord<>(
        KafkaProducerExample.TOPIC_NAME,
        partition,
        key,
        value
    );

    System.out.println("sending record");
    producer.send(recordToSend);
    System.out.println("record sent");
    producer.close();
    System.out.println("Ending Kafka Producer Example");
  }


}
