package org.example.simple;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.factories.KafkaConsumerFactory;
import properties.KafkaProducerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;


public class KafkaConsumerExample {

  private static final Logger log = LoggerFactory.getLogger(KafkaConsumerExample.class);

  public static void main(String[] args) {

    try (KafkaConsumer<String, String> consumer = KafkaConsumerFactory.getKafkaConsumer()) {
      consumer.subscribe(Collections.singletonList(KafkaProducerProperties.TOPIC_NAME));

      while (true) {
        ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(10));

        for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
          log.info("Topic: {}, Partition: {}, Offset: {}, Key: {}, Value: {}",
              consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(),
              consumerRecord.key(), consumerRecord.value());
        }
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }


}
