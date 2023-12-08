package lab.kafka.transactional;

import lab.kafka.factories.KafkaConsumerFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import lab.properties.KafkaProducerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;

public class KafkaConsumerTransactional {
  private static final Logger log = LoggerFactory.getLogger(KafkaConsumerTransactional.class);

  public static void main(String[] args) {

    try (KafkaConsumer<String, String> consumer = KafkaConsumerFactory.getKafkaConsumerWithTransaction()) {
      consumer.subscribe(Collections.singletonList(KafkaProducerProperties.TOPIC_NAME));

      long index = 0;
      while (true) {
        ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(10));

        for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
          log.debug("Topic: {}, Partition: {}, Offset: {}, Key: {}, Value: {}",
              consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(),
              consumerRecord.key(), consumerRecord.value());

          if (index % 100000L == 0) {
            log.info("Topic: {}, Partition: {}, Offset: {}, Key: {}, Value: {}",
                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(),
                consumerRecord.key(), consumerRecord.value());
          }
          index++;
        }
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }


}
