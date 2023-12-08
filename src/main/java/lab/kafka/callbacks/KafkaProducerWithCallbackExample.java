package lab.kafka.callbacks;

import lab.kafka.factories.KafkaProducerFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class KafkaProducerWithCallbackExample {

  public static void main(String[] args) {
    KafkaProducer<String, String> producer = KafkaProducerFactory.getKafkaProducer();
    String topic = "test-topic-2";
    String key = "testKey";
    String value = "testValue";
    final ProducerRecord<String, String> record = new
        ProducerRecord<>(topic, key, value);
    producer.send(record, (recordMetadata, exception) -> {
      if (exception != null) {
        System.out.println("Send failed for record: " + exception.getMessage());
      }
      System.out.println("Record sent to partition " + recordMetadata.partition() +
          " with offset " + recordMetadata.offset());
    });

    producer.close();
  }



}
