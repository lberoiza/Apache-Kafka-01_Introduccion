package properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

public class KafkaProducerProperties {

  public static final String TOPIC_NAME = "test-topic";


  public static Properties getProperties() {
    Properties props = new Properties();
    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
    props.put("acks", "all");
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092,localhost:9093,localhost:9094");
    props.put("retries", 0);
    props.put("batch.size", 16384);
    props.put("buffer.memory", 33554432);
    return props;
  }

}
