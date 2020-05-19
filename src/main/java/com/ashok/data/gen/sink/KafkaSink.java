package com.ashok.data.gen.sink;

import java.util.Map;
//import util.properties packages
import java.util.Properties;

//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;
//import simple producer packages
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.ashok.data.gen.constant.DataGenerationConstant;

public class KafkaSink implements DataSink {

  private Producer<String, String> producer;
  
  private String topic;

  public KafkaSink(Map<String, String> sinkInfo) {
    init(sinkInfo);
  }

  private void init(Map<String, String> sinkInfo) {
    // Assign topicName to string variable
    this.topic = sinkInfo.get(DataGenerationConstant.TOPIC);
    String server = sinkInfo.get(DataGenerationConstant.BOOTSTRAP_SERVER);
    String acks = sinkInfo.get(DataGenerationConstant.ACK);
    String retry = sinkInfo.get(DataGenerationConstant.RETRY);
    String batchSize = sinkInfo.get(DataGenerationConstant.BATCH_SIZE);
    // create instance for properties to access producer configs
    Properties props = new Properties();
    
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    props.put(ProducerConfig.ACKS_CONFIG, acks);
    props.put(ProducerConfig.RETRIES_CONFIG, retry);
    props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
    producer = new KafkaProducer<String, String>(
        props);
  }

  @Override
  public void write(String line) throws Exception {
    producer.send(new ProducerRecord<String, String>(topic,line));

  }

  @Override
  public void close() {
    producer.close();

  }

}
