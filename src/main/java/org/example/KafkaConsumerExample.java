package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;


public class KafkaConsumerExample {

  private static KafkaConsumer<String, String> getKafkaConsumer() {
    Properties props = new Properties();
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
    props.put("group.id", "grupo1");
    props.put("enable.auto.commit", "true");
    props.put("auto.commit.interval.ms", "1000");
    props.put("fetch.min.bytes", "1");
    props.put("fetch.max.wait.ms", "500");
    props.put("max.partition.fetch.bytes", "1048576");
    props.put("session.timeout.ms", "10000");
    return new KafkaConsumer<>(props);
  }


  public static void main(String[] args) {

    try (KafkaConsumer<String, String> consumer = getKafkaConsumer()) {
      consumer.subscribe(Collections.singletonList(KafkaProducerExample.TOPIC_NAME));

      while (true) {
        ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(10));

        for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {

          System.out.print("Topic: " + consumerRecord.topic() + ", ");
          System.out.print("Partition: " + consumerRecord.partition() + ", ");
          System.out.print("Offset: " + consumerRecord.offset() + ", ");

          System.out.print("Key:" + consumerRecord.key() + ", ");
          System.out.println("Value: " + consumerRecord.value());
        }
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }


}
