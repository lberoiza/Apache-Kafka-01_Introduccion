package org.example.factories;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.properties.KafkaProducerProperties;

import java.util.Properties;

public class KafkaConsumerFactory {

  public static KafkaConsumer<String, String> getKafkaConsumer() {
    Properties props = KafkaProducerProperties.getProperties();
    return new KafkaConsumer<>(props);
  }

  public static KafkaConsumer<String, String> getKafkaConsumerWithTransaction() {
    Properties props = KafkaProducerProperties.getProperties();
    props.put("isolation.level", "read_committed");
    return new KafkaConsumer<>(props);
  }

}