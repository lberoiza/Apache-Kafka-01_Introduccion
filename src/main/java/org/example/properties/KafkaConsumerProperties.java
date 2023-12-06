package org.example.properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

public class KafkaConsumerProperties {

  public static Properties getProperties() {
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
    return props;
  }

}
