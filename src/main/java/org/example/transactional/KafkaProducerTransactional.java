package org.example.transactional;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.factories.KafkaProducerFactory;
import org.example.properties.KafkaProducerProperties;
import org.slf4j.Logger;

public class KafkaProducerTransactional {

  public static final Logger logger = org.slf4j.LoggerFactory.getLogger(KafkaProducerTransactional.class);
  public static void main(String[] args) {
    long nrMessages = 1000000L;
    String transactionId = "test-transactional-id";
    String key = "testKey-";
    String value = "testValue-";

    KafkaProducer<String, String> producer = KafkaProducerFactory.getKafkaProducerWithTransaction(transactionId);

    long startTime = System.currentTimeMillis();
    producer.initTransactions();
    producer.beginTransaction();
    for(long i =0; i < nrMessages; i++) {
      ProducerRecord<String, String> recordToSend = new ProducerRecord<>(
          KafkaProducerProperties.TOPIC_NAME,
          key + i,
          value + i
      );
      producer.send(recordToSend);
    }
    producer.commitTransaction();
    producer.flush();
    producer.close();
    logger.info("Time to send {} messages: {} ms", nrMessages, System.currentTimeMillis() - startTime);
  }


}