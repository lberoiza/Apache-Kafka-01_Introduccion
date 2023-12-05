package org.example.multithread;

import org.example.KafkaConsumerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaConsumerMultiThreadExample {

  private static Integer NR_THREAD = 5;

  public static void main(String[] args) {
    ExecutorService threadExecutor = Executors.newFixedThreadPool(NR_THREAD);

    for(int i=0; i< NR_THREAD; i++) {
      KafkaConsumerMultiThread consumerMultiThread = new KafkaConsumerMultiThread(KafkaConsumerFactory.getKafkaConsumer());
      threadExecutor.execute(consumerMultiThread);
    }

    while(!threadExecutor.isTerminated());

  }



}
