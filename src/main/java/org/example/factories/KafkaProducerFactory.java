package org.example.factories;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.example.properties.KafkaProducerProperties;

import java.util.Properties;


public class KafkaProducerFactory {

  private static KafkaProducer<String, String> producer = null;

  private static KafkaProducer<String, String> createKafkaProducer() {
    Properties props = KafkaProducerProperties.getProperties();
    return new KafkaProducer<>(props);
  }

  public static KafkaProducer<String, String> getKafkaProducer(){
    if(producer == null) {
      producer = createKafkaProducer();
    }
    return producer;
  }


}
