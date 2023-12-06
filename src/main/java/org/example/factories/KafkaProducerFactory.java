package org.example.factories;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;


public class KafkaProducerFactory {

  private static KafkaProducer<String, String> producer = null;

  private static KafkaProducer<String, String> createKafkaProducer() {
    Properties props = new Properties();
    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
    props.put("acks", "all");
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092,localhost:9093,localhost:9094");
    props.put("retries", 0);
    props.put("batch.size", 16384);
    props.put("buffer.memory", 33554432);

    return new KafkaProducer<>(props);
  }

  public static KafkaProducer<String, String> getKafkaProducer(){
    if(producer == null) {
      producer = createKafkaProducer();
    }
    return producer;
  }


}
